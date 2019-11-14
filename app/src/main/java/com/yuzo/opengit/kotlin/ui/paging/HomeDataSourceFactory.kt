package com.yuzo.opengit.kotlin.ui.paging

import com.yuzo.lib.ui.paging.AbsDataSourceFactory
import com.yuzo.opengit.kotlin.http.service.bean.Entrylist

/**
 * Author: yuzo
 * Date: 2019-10-09
 */
class HomeDataSourceFactory : AbsDataSourceFactory<Entrylist, HomeDataSource>() {
    override fun getDataSource(): HomeDataSource = HomeDataSource()

    companion object {
        private const val TAG = "HomeDataSourceFactory"
    }
}