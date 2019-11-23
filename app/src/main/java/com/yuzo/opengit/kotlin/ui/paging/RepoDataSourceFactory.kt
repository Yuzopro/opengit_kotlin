package com.yuzo.opengit.kotlin.ui.paging

import androidx.lifecycle.MutableLiveData
import androidx.paging.DataSource
import com.yuzo.lib.log.v
import com.yuzo.lib.ui.paging.AbsDataSourceFactory
import com.yuzo.opengit.kotlin.http.service.bean.Entrylist
import com.yuzo.opengit.kotlin.http.service.bean.Repo
import com.yuzo.opengit.kotlin.ui.repository.RepoRepository

/**
 * Author: yuzo
 * Date: 2019-10-09
 */
class RepoDataSourceFactory(val name: String?,params : Map<String, Any>) : AbsDataSourceFactory<Repo, RepoDataSource>(params) {
    override fun getDataSource(params : Map<String, Any>): RepoDataSource = RepoDataSource(name, params)

    companion object {
        private const val TAG = "RepoDataSourceFactory"
    }
}