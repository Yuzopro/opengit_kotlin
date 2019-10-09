package com.yuzo.opengit.kotlin.ui.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.paging.LivePagedListBuilder
import androidx.paging.PagedList
import com.yuzo.opengit.kotlin.ui.paging.HomeDataSourceFactory
import com.yuzo.opengit.kotlin.ui.repository.HomeRepository

/**
 * Author: yuzo
 * Date: 2019-09-26
 */
class HomeViewModel constructor(private val repository: HomeRepository) : ViewModel() {
    companion object {
        private const val TAG: String = "HomeViewModel"
    }

    private val config = PagedList.Config.Builder()
        .setPageSize(10)    //每页显示的词条数
        .setEnablePlaceholders(false)
        .setInitialLoadSizeHint(10) //首次加载的数据量
        .setPrefetchDistance(5)     //距离底部还有多少条数据时开始预加载
        .build()

    val homes : LiveData<PagedList<String>> = LivePagedListBuilder(HomeDataSourceFactory(), config)
        .build()
}