package com.yuzo.lib.ui.paging

import androidx.lifecycle.MutableLiveData
import androidx.paging.PositionalDataSource
import com.yuzo.lib.ui.repository.NetworkState
import java.util.concurrent.Executors

/**
 * Author: yuzo
 * Date: 2019-10-10
 */
abstract class BasePositionalDataSource<T> : PositionalDataSource<T>() {
    @Suppress("PrivatePropertyName")
    private val NETWORK_IO = Executors.newFixedThreadPool(5)

    var retry: (() -> Any)? = null

    val networkState = MutableLiveData<NetworkState>()

    fun retryAllFailed() {
        val prevRetry = retry
        retry = null
        prevRetry?.let {
            NETWORK_IO.execute {
                it.invoke()
            }
        }
    }

    companion object {
        private const val TAG = "BasePositionalDataSource"
    }
}