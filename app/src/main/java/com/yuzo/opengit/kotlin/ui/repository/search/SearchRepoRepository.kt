package com.yuzo.opengit.kotlin.ui.repository.search

import com.google.gson.Gson
import com.yuzo.lib.ui.paging.AbsDataSourceFactory
import com.yuzo.lib.ui.repository.AbsRepository
import com.yuzo.opengit.kotlin.http.service.bean.Repo
import com.yuzo.opengit.kotlin.http.service.bean.User
import com.yuzo.opengit.kotlin.sp.userSp
import com.yuzo.opengit.kotlin.ui.paging.search.SearchRepoDataSource
import com.yuzo.opengit.kotlin.ui.paging.search.SearchRepoDataSourceFactory

/**
 * Author: yuzo
 * Date: 2019-10-09
 */
class SearchRepoRepository  : AbsRepository<Repo, SearchRepoDataSource>() {

    private var user: User? = null

    init {
        user = Gson().fromJson(userSp, User::class.java)
    }

    override fun getDataSourceFactory(params : Map<String, Any>): AbsDataSourceFactory<Repo, SearchRepoDataSource> = SearchRepoDataSourceFactory(params)

    companion object {
        private const val TAG = "RepoRepository"
    }
}