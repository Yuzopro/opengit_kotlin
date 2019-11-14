package com.yuzo.opengit.kotlin.ui.paging

import androidx.lifecycle.MutableLiveData
import com.yuzo.lib.log.v
import com.yuzo.lib.ui.paging.BasePositionalDataSource
import com.yuzo.opengit.kotlin.http.HttpClient2
import com.yuzo.opengit.kotlin.http.service.bean.Entrylist
import com.yuzo.lib.ui.repository.NetworkState
import java.io.IOException

/**
 * Author: yuzo
 * Date: 2019-10-09
 */
class HomeDataSource : BasePositionalDataSource<Entrylist>() {

    override fun loadInitial(params: LoadInitialParams, callback: LoadInitialCallback<Entrylist>) {
        v(TAG, "loadInitial")

        val request = HttpClient2.getInstance().homeService.queryHomes(
            1,
            params.requestedLoadSize
        )
        networkState.postValue(NetworkState.LOADING)
        initialLoad.postValue(NetworkState.LOADING)

        try {
            val response = request.execute()
            val data = response.body()?.d
            val items = data?.entrylist?.map { it } ?: emptyList()
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

    override fun loadRange(params: LoadRangeParams, callback: LoadRangeCallback<Entrylist>) {
        v(TAG, "loadRange")

//        val index = params.startPosition % params.loadSize
        val page = params.startPosition / params.loadSize

        val request = HttpClient2.getInstance().homeService.queryHomes(
            page,
            params.loadSize
        )
        networkState.postValue(NetworkState.LOADING)
        initialLoad.postValue(NetworkState.LOADING)

        try {
            val response = request.execute()
            val data = response.body()?.d
            val items = data?.entrylist?.map { it } ?: emptyList()
            retry = null
            networkState.postValue(NetworkState.LOADED)
            initialLoad.postValue(NetworkState.LOADED)
            callback.onResult(items)
        } catch (ioException: IOException) {
            retry = {
                loadRange(params, callback)
            }
            val error = NetworkState.error(ioException.message ?: "unknown error")
            networkState.postValue(error)
            initialLoad.postValue(error)
        }
    }

    companion object {
        private const val TAG = "HomeDataSource"
    }
}