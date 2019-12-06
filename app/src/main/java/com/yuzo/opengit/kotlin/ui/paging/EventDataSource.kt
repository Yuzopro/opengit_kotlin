package com.yuzo.opengit.kotlin.ui.paging

import com.yuzo.lib.http.NetworkScheduler
import com.yuzo.lib.log.v
import com.yuzo.lib.ui.paging.BasePositionalDataSource
import com.yuzo.lib.ui.repository.NetworkState
import com.yuzo.opengit.kotlin.http.HttpClient
import com.yuzo.opengit.kotlin.http.service.bean.Event
import java.io.IOException

/**
 * Author: yuzo
 * Date: 2019-10-09
 */
class EventDataSource(val name: String?, params : Map<String, Any>) : BasePositionalDataSource<Event>() {

    override fun loadInitial(params: LoadInitialParams, callback: LoadInitialCallback<Event>) {
        v(TAG, "loadInitial")

        val request = HttpClient.getInstance().userService.queryReceivedEvents(
            name,
            1,
            params.requestedLoadSize
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

    override fun loadRange(params: LoadRangeParams, callback: LoadRangeCallback<Event>) {
        v(TAG, "loadRange")

        val index = params.startPosition % params.loadSize
        if (index != 0) {
            return
        }
        val page = params.startPosition / params.loadSize + 1

        val request = HttpClient.getInstance().userService.queryReceivedEvents(
            name,
            page,
            params.loadSize
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
        private const val TAG = "EventDataSource"
    }
}