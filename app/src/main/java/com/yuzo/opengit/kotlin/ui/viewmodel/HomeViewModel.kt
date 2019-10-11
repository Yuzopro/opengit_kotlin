package com.yuzo.opengit.kotlin.ui.viewmodel

import androidx.lifecycle.LiveData
import androidx.paging.LivePagedListBuilder
import androidx.paging.PagedList
import com.yuzo.lib.ui.viewmodel.BaseRefreshViewModel
import com.yuzo.opengit.kotlin.http.service.bean.Entrylist
import com.yuzo.opengit.kotlin.ui.paging.HomeDataSource
import com.yuzo.opengit.kotlin.ui.paging.HomeDataSourceFactory
import com.yuzo.opengit.kotlin.ui.repository.HomeRepository

/**
 * Author: yuzo
 * Date: 2019-09-26
 */
class HomeViewModel constructor(repository: HomeRepository) :
    BaseRefreshViewModel<Entrylist, HomeDataSource>() {

    override var lists: LiveData<PagedList<Entrylist>> =
        LivePagedListBuilder(HomeDataSourceFactory(dataSource, repository), config)
            .build()

    companion object {
        private const val TAG: String = "HomeViewModel"
    }

}