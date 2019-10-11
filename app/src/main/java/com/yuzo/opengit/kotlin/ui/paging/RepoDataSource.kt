package com.yuzo.opengit.kotlin.ui.paging

import com.yuzo.lib.http.ResponseObserver
import com.yuzo.lib.log.v
import com.yuzo.lib.tool.ToastUtil
import com.yuzo.lib.ui.paging.BasePositionalDataSource
import com.yuzo.opengit.kotlin.http.service.bean.Repo
import com.yuzo.opengit.kotlin.ui.repository.RepoRepository

/**
 * Author: yuzo
 * Date: 2019-10-09
 */
class RepoDataSource constructor(private val repository: RepoRepository) :
    BasePositionalDataSource<Repo>() {

    override fun loadInitial(params: LoadInitialParams, callback: LoadInitialCallback<Repo>) {
        v(TAG, "loadInitial")

        showLoading()

        repository.queryRepos(1, params.requestedLoadSize, object : ResponseObserver<List<Repo>>() {
            override fun onSuccess(response: List<Repo>?) {
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

    override fun loadRange(params: LoadRangeParams, callback: LoadRangeCallback<Repo>) {
        v(TAG, "loadRange")

        val page = params.startPosition / params.loadSize
        repository.queryRepos(page, params.loadSize, object : ResponseObserver<List<Repo>>() {
            override fun onSuccess(response: List<Repo>?) {
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
        private const val TAG = "RepoDataSource"
    }
}