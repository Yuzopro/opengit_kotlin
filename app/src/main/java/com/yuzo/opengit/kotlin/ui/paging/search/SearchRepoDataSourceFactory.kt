package com.yuzo.opengit.kotlin.ui.paging.search

import com.yuzo.lib.ui.paging.AbsDataSourceFactory
import com.yuzo.opengit.kotlin.http.service.bean.Repo

/**
 * Author: yuzo
 * Date: 2019-10-09
 */
class SearchRepoDataSourceFactory : AbsDataSourceFactory<Repo, SearchRepoDataSource>() {
    override fun getDataSource(): SearchRepoDataSource = SearchRepoDataSource()

    companion object {
        private const val TAG = "RepoDataSourceFactory"
    }
}