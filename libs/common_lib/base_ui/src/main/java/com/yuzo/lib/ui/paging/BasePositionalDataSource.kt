package com.yuzo.lib.ui.paging

import androidx.lifecycle.MutableLiveData
import androidx.paging.PositionalDataSource
import com.yuzo.lib.ui.repository.NetworkState

/**
 * Author: yuzo
 * Date: 2019-10-10
 */
abstract class BasePositionalDataSource<T> : PositionalDataSource<T>() {

    var retry: (() -> Any)? = null

    val initialLoad = MutableLiveData<NetworkState>()
    val networkState = MutableLiveData<NetworkState>()

    fun retryAllFailed() {
        val prevRetry = retry
        retry = null
        prevRetry?.let {
            it.invoke()
        }
    }

    companion object {
        private const val TAG = "BasePositionalDataSource"
    }
}