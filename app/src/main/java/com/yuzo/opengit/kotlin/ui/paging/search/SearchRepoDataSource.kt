package com.yuzo.opengit.kotlin.ui.paging.search

import com.yuzo.lib.log.v
import com.yuzo.lib.ui.paging.BasePositionalDataSource
import com.yuzo.lib.ui.repository.NetworkState
import com.yuzo.opengit.kotlin.http.HttpClient
import com.yuzo.opengit.kotlin.http.service.bean.Repo
import java.io.IOException

/**
 * Author: yuzo
 * Date: 2019-10-09
 */
class SearchRepoDataSource(val params : Map<String, Any>) : BasePositionalDataSource<Repo>() {
    override fun loadInitial(params: LoadInitialParams, callback: LoadInitialCallback<Repo>) {
        v(TAG, "loadInitial")

        val request = HttpClient.getInstance().searchService.searchRepo(
            this.params["q"] as String,
            1,
            params.requestedLoadSize
        )
        networkState.postValue(NetworkState.LOADING)

        try {
            val response = request.execute()
            val data = response.body()
            retry = null
            val list = data?.items?.map { it } ?: emptyList()
            networkState.postValue(if (list.isEmpty()) NetworkState.NO_DATA else NetworkState.LOADED)
            callback.onResult(list, 0)
        } catch (ioException: IOException) {
            retry = {
                loadInitial(params, callback)
            }
            val error = NetworkState.error(ioException.message ?: "unknown error")
            networkState.postValue(error)
        }
    }

    override fun loadRange(params: LoadRangeParams, callback: LoadRangeCallback<Repo>) {
        v(TAG, "loadRange")
        val index = params.startPosition % params.loadSize
        if (index != 0) {
            return
        }
        val page = params.startPosition / params.loadSize

        val request = HttpClient.getInstance().searchService.searchRepo(
            this.params["q"] as String,
            page,
            params.loadSize
        )

        try {
            val response = request.execute()
            val data = response.body()
            val items = data?.items?.map { it } ?: emptyList()
            retry = null
            networkState.postValue(NetworkState.LOADED)
            callback.onResult(items)
        } catch (ioException: IOException) {
            retry = {
                loadRange(params, callback)
            }
            val error = NetworkState.loadError(ioException.message ?: "unknown error")
            networkState.postValue(error)
        }
    }

    companion object {
        private const val TAG = "SearchRepoDataSource"
    }
}