package com.yuzo.opengit.kotlin.ui.repository

import com.yuzo.lib.ui.paging.AbsDataSourceFactory
import com.yuzo.lib.ui.repository.AbsRepository
import com.yuzo.opengit.kotlin.http.service.bean.Entrylist
import com.yuzo.opengit.kotlin.ui.paging.HomeDataSource
import com.yuzo.opengit.kotlin.ui.paging.HomeDataSourceFactory

/**
 * Author: yuzo
 * Date: 2019-11-14
 */
class HomeRepository : AbsRepository<Entrylist, HomeDataSource>() {
    override fun getDataSourceFactory(params : Map<String, Any>): AbsDataSourceFactory<Entrylist, HomeDataSource> =
        HomeDataSourceFactory(params)
}