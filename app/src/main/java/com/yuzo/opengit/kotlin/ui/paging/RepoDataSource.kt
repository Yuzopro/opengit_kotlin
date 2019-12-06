package com.yuzo.opengit.kotlin.ui.paging

import com.yuzo.lib.log.v
import com.yuzo.lib.ui.paging.BasePositionalDataSource
import com.yuzo.lib.ui.repository.NetworkState
import com.yuzo.opengit.kotlin.http.HttpClient
import com.yuzo.opengit.kotlin.http.HttpClient2
import com.yuzo.opengit.kotlin.http.service.bean.Repo
import java.io.IOException

/**
 * Author: yuzo
 * Date: 2019-10-09
 */
class RepoDataSource(val name: String?, params : Map<String, Any>) : BasePositionalDataSource<Repo>() {

    override fun loadInitial(params: LoadInitialParams, callback: LoadInitialCallback<Repo>) {
        v(TAG, "loadInitial page is 1")

        val request = HttpClient.getInstance().userService.queryRepos(
            name,
            1,
            params.requestedLoadSize, "pushed"
        )
        networkState.postValue(NetworkState.LOADING)

        try {
            val response = request.execute()
            val data = response.body()
            val items = data?.map { it } ?: emptyList()
            retry = null
            networkState.postValue(if (items.isEmpty()) NetworkState.NO_DATA else NetworkState.LOADED)
            callback.onResult(items, 0)
        } catch (ioException: IOException) {
            retry = {
                loadInitial(params, callback)
            }
            val error = NetworkState.error(ioException.message ?: "unknown error")
            networkState.postValue(error)
        }
    }

    override fun loadRange(params: LoadRangeParams, callback: LoadRangeCallback<Repo>) {
        val index = params.startPosition % params.loadSize
        if (index != 0) {
            return
        }
        val page = params.startPosition / params.loadSize + 1

        v(TAG, "loadRange page is " + page)

        val request = HttpClient.getInstance().userService.queryRepos(
            name,
            page,
            params.loadSize, "pushed"
        )

        try {
            val response = request.execute()
            val data = response.body()
            val items = data?.map { it } ?: emptyList()
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
        private const val TAG = "RepoDataSource"
    }
}