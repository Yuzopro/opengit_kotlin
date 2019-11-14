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
class EventDataSource(val name: String?) : BasePositionalDataSource<Event>() {

    override fun loadInitial(params: LoadInitialParams, callback: LoadInitialCallback<Event>) {
        v(TAG, "loadInitial")

        val request = HttpClient.getInstance().userService.queryReceivedEvents(
            name,
            1,
            params.requestedLoadSize
        )
        networkState.postValue(NetworkState.LOADING)
        initialLoad.postValue(NetworkState.LOADING)

        try {
            val response = request.execute()
            val data = response.body()
            val items = data?.map { it } ?: emptyList()
            retry = null
            networkState.postValue(NetworkState.LOADED)
            initialLoad.postValue(NetworkState.LOADED)
            callback.onResult(items, 0)
        } catch (ioException: IOException) {
            retry = {
                loadInitial(params, callback)
            }
            val error = NetworkState.error(ioException.message ?: "unknown error")
            networkState.postValue(error)
            initialLoad.postValue(error)
        }
    }

    override fun loadRange(params: LoadRangeParams, callback: LoadRangeCallback<Event>) {
        v(TAG, "loadRange")

//        val index = params.startPosition % params.loadSize
//        if (index == 0) {
//            val page = params.startPosition / params.loadSize
//            repository.queryEvents(page, params.loadSize, object : ResponseObserver<List<Event>>() {
//                override fun onSuccess(response: List<Event>?) {
//                    response?.apply {
//                        callback.onResult(this)
//                    }
//                }
//
//                override fun onError(code: Int, message: String) {
//                    ToastUtil.showShort(message)
//                }
//            })
//        }
    }

    companion object {
        private const val TAG = "EventDataSource"
    }
}