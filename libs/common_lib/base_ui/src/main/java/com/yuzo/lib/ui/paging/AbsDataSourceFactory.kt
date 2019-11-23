package com.yuzo.lib.ui.paging

import androidx.lifecycle.MutableLiveData
import androidx.paging.DataSource

/**
 * Author: yuzo
 * Date: 2019-11-14
 */
abstract class AbsDataSourceFactory<T, S : BasePositionalDataSource<T>>(val params : Map<String, Any>) : DataSource.Factory<Int, T>() {
    val sourceLiveData = MutableLiveData<S>()

    abstract fun getDataSource(params : Map<String, Any>) : S

    override fun create(): DataSource<Int, T> {
        val source = getDataSource(params)
        sourceLiveData.postValue(source)
        return source
    }
}