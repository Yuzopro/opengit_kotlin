package com.yuzo.opengit.kotlin.ui.repository

import com.google.gson.Gson
import com.yuzo.lib.ui.paging.AbsDataSourceFactory
import com.yuzo.lib.ui.repository.AbsRepository
import com.yuzo.opengit.kotlin.http.service.bean.Repo
import com.yuzo.opengit.kotlin.http.service.bean.User
import com.yuzo.opengit.kotlin.sp.userSp
import com.yuzo.opengit.kotlin.ui.paging.RepoDataSource
import com.yuzo.opengit.kotlin.ui.paging.RepoDataSourceFactory

/**
 * Author: yuzo
 * Date: 2019-10-09
 */
class RepoRepository  : AbsRepository<Repo, RepoDataSource>() {

    private var user: User? = null

    init {
        user = Gson().fromJson(userSp, User::class.java)
    }

    override fun getDataSourceFactory(): AbsDataSourceFactory<Repo, RepoDataSource> = RepoDataSourceFactory(user?.login)

    companion object {
        private const val TAG = "RepoRepository"
    }
}