package com.yuzo.opengit.kotlin.ui.paging.search

import com.yuzo.lib.ui.paging.AbsDataSourceFactory
import com.yuzo.opengit.kotlin.http.service.bean.Repo

/**
 * Author: yuzo
 * Date: 2019-10-09
 */
class SearchRepoDataSourceFactory(params : Map<String, Any>) : AbsDataSourceFactory<Repo, SearchRepoDataSource>(params) {
    override fun getDataSource(params : Map<String, Any>): SearchRepoDataSource = SearchRepoDataSource(params)

    companion object {
        private const val TAG = "RepoDataSourceFactory"
    }
}