package com.yuzo.opengit.kotlin.ui.paging

import com.yuzo.lib.ui.paging.AbsDataSourceFactory
import com.yuzo.opengit.kotlin.http.service.bean.Entrylist

/**
 * Author: yuzo
 * Date: 2019-10-09
 */
class HomeDataSourceFactory(params : Map<String, Any>) : AbsDataSourceFactory<Entrylist, HomeDataSource>(params) {
    override fun getDataSource(params : Map<String, Any>): HomeDataSource = HomeDataSource(params)

    companion object {
        private const val TAG = "HomeDataSourceFactory"
    }
}