package com.yuzo.opengit.kotlin.ui.paging

import com.yuzo.lib.http.ResponseObserver
import com.yuzo.lib.log.v
import com.yuzo.lib.tool.ToastUtil
import com.yuzo.lib.ui.paging.BasePositionalDataSource
import com.yuzo.opengit.kotlin.http.service.bean.Entrylist
import com.yuzo.opengit.kotlin.http.service.bean.Home
import com.yuzo.opengit.kotlin.ui.repository.HomeRepository

/**
 * Author: yuzo
 * Date: 2019-10-09
 */
class HomeDataSource constructor(private val repository: HomeRepository) :
    BasePositionalDataSource<Entrylist>() {

    override fun loadInitial(params: LoadInitialParams, callback: LoadInitialCallback<Entrylist>) {
        v(TAG, "loadInitial")

        showLoading()

        val pageSize = params.requestedLoadSize
        repository.queryHomes(1, pageSize, object : ResponseObserver<Home>() {
            override fun onSuccess(response: Home?) {
                response?.apply {
                    callback.onResult(d.entrylist, 0)
                }

                if (response == null || response.d.entrylist.isEmpty()) {
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

    override fun loadRange(params: LoadRangeParams, callback: LoadRangeCallback<Entrylist>) {
        v(TAG, "loadRange")

        val index = params.startPosition % params.loadSize
        if (index == 0) {
            val page = params.startPosition / params.loadSize
            repository.queryHomes(page, params.loadSize, object : ResponseObserver<Home>() {
                override fun onSuccess(response: Home?) {
                    response?.apply {
                        callback.onResult(d.entrylist)
                    }
                }

                override fun onError(code: Int, message: String) {
                    ToastUtil.showShort(message)
                }
            })
        }
    }

    companion object {
        private const val TAG = "HomeDataSource"
    }
}