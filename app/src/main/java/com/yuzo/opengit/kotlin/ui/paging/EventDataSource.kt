package com.yuzo.opengit.kotlin.ui.paging

import com.yuzo.lib.http.ResponseObserver
import com.yuzo.lib.log.v
import com.yuzo.lib.tool.ToastUtil
import com.yuzo.lib.ui.paging.BasePositionalDataSource
import com.yuzo.opengit.kotlin.http.service.bean.Event
import com.yuzo.opengit.kotlin.http.service.bean.Repo
import com.yuzo.opengit.kotlin.ui.repository.EventRepository

/**
 * Author: yuzo
 * Date: 2019-10-09
 */
class EventDataSource constructor(private val repository: EventRepository) :
    BasePositionalDataSource<Event>() {

    override fun loadInitial(params: LoadInitialParams, callback: LoadInitialCallback<Event>) {
        v(TAG, "loadInitial")

        showLoading()

        repository.queryEvents(1, params.requestedLoadSize, object : ResponseObserver<List<Event>>() {
            override fun onSuccess(response: List<Event>?) {
                response?.apply {
                    callback.onResult(this, 0)
                }

                if (response == null || response.isEmpty()) {
                    showEmptyView()
                } else {
                    hideLoading()
                }
            }

            override fun onError(code: Int, message: String) {
                ToastUtil.showShort(message)
                showErrorView()
            }
        })
    }

    override fun loadRange(params: LoadRangeParams, callback: LoadRangeCallback<Event>) {
        v(TAG, "loadRange")

        val page = params.startPosition / params.loadSize
        repository.queryEvents(page, params.loadSize, object : ResponseObserver<List<Event>>() {
            override fun onSuccess(response: List<Event>?) {
                response?.apply {
                    callback.onResult(this)
                }
            }

            override fun onError(code: Int, message: String) {
                ToastUtil.showShort(message)
            }
        })
    }

    companion object {
        private const val TAG = "EventDataSource"
    }
}