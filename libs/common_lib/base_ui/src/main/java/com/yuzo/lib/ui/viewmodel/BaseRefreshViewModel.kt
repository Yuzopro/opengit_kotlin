package com.yuzo.lib.ui.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations.switchMap
import androidx.paging.PagedList
import com.yuzo.lib.ui.paging.BasePositionalDataSource

/**
 * Author: yuzo
 * Date: 2019-10-10
 */
abstract class BaseRefreshViewModel<T, P : BasePositionalDataSource<T>> : BaseViewModel() {
    open val config = PagedList.Config.Builder()
        .setPageSize(20)    //每页显示的词条数
        .setEnablePlaceholders(false)
        .setInitialLoadSizeHint(20) //首次加载的数据量
        .setPrefetchDistance(5)     //距离底部还有多少条数据时开始预加载
        .build()

    abstract var lists: LiveData<PagedList<T>>

    val dataSource = MutableLiveData<P>()

    val loading = switchMap(dataSource) { it.getLoadingBean() }

    fun onRefresh() {
        dataSource.value?.onRefresh()
    }


}