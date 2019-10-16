package com.yuzo.opengit.kotlin.ui.paging

import com.yuzo.lib.http.ResponseObserver
import com.yuzo.lib.log.v
import com.yuzo.lib.tool.ToastUtil
import com.yuzo.lib.ui.paging.BasePositionalDataSource
import com.yuzo.opengit.kotlin.http.service.bean.Issue
import com.yuzo.opengit.kotlin.ui.repository.IssueRepository

/**
 * Author: yuzo
 * Date: 2019-10-09
 */
class IssueDataSource constructor(
    private val repository: IssueRepository, private var filter: String,
    private val state: String,
    private val sort: String,
    private val direction: String
) : BasePositionalDataSource<Issue>() {

    override fun loadInitial(params: LoadInitialParams, callback: LoadInitialCallback<Issue>) {
        v(TAG, "loadInitial")

        showLoading()

        val pageSize = params.requestedLoadSize
        repository.queryIssues(filter, state, sort, direction,
            1,
            pageSize,
            object : ResponseObserver<List<Issue>>() {
                override fun onSuccess(response: List<Issue>?) {
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

    override fun loadRange(params: LoadRangeParams, callback: LoadRangeCallback<Issue>) {
        v(TAG, "loadRange")

        val index = params.startPosition % params.loadSize
        if (index == 0) {
            val page = params.startPosition / params.loadSize
            repository.queryIssues(filter, state, sort, direction,
                page,
                params.loadSize,
                object : ResponseObserver<List<Issue>>() {
                    override fun onSuccess(response: List<Issue>?) {
                        response?.apply {
                            callback.onResult(this)
                        }
                    }

                    override fun onError(code: Int, message: String) {
                        ToastUtil.showShort(message)
                    }
                })
        }
    }

    companion object {
        private const val TAG = "IssueDataSource"
    }
}