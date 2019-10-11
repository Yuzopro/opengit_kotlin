package com.yuzo.lib.ui.paging

import androidx.lifecycle.MutableLiveData
import androidx.paging.PositionalDataSource

/**
 * Author: yuzo
 * Date: 2019-10-10
 */
abstract class BasePositionalDataSource<T> : PositionalDataSource<T>() {
    val loading = MutableLiveData<LoadingBean>()

    fun onRefresh() {
        invalidate()
    }

    fun getLoadingBean(): MutableLiveData<LoadingBean> {
        return loading
    }

    fun showLoading() {
        loading.postValue(LoadingBean(LoadingState.Loading))
    }

    fun hideLoading() {
        loading.postValue(LoadingBean(LoadingState.Normal))
    }

    fun showErrorView() {
        loading.postValue(LoadingBean(LoadingState.Failed))
    }

    fun showEmptyView() {
        loading.postValue(LoadingBean(LoadingState.Empty))
    }

    companion object {
        private const val TAG = "BasePositionalDataSource"
    }
}