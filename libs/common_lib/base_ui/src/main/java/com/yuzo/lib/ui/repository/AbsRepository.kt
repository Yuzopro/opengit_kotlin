package com.yuzo.lib.ui.repository

import androidx.lifecycle.Transformations
import androidx.paging.LivePagedListBuilder
import androidx.paging.PagedList
import com.yuzo.lib.ui.paging.AbsDataSourceFactory
import com.yuzo.lib.ui.paging.BasePositionalDataSource

/**
 * Author: yuzo
 * Date: 2019-11-14
 */
abstract class AbsRepository<T, S : BasePositionalDataSource<T>> : BaseRepository<T>  {
    abstract fun getDataSourceFactory() : AbsDataSourceFactory<T, S>

    lateinit var sourceFactory : AbsDataSourceFactory<T, S>

    override fun post(state: Int, pageSize: Int): Listing<T> {
        sourceFactory = getDataSourceFactory()

        val config = PagedList.Config.Builder()
            .setPageSize(pageSize)    //每页显示的词条数
            .setEnablePlaceholders(false)
            .setInitialLoadSizeHint(pageSize) //首次加载的数据量
            .setPrefetchDistance(5)     //距离底部还有多少条数据时开始预加载
            .build()

        val livePagedList = LivePagedListBuilder(sourceFactory, config)
            .build()
        val networkState = Transformations.switchMap(sourceFactory.sourceLiveData) {
            it.networkState
        }

        return Listing(
            pagedList = livePagedList,
            networkState = networkState,
            retry = {
                sourceFactory.sourceLiveData.value?.retryAllFailed()
            },
            refresh = {
                sourceFactory.sourceLiveData.value?.invalidate()
            }
        )
    }
}