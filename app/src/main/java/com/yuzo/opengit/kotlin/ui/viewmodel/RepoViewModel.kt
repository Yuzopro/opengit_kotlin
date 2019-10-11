package com.yuzo.opengit.kotlin.ui.viewmodel

import androidx.lifecycle.LiveData
import androidx.paging.LivePagedListBuilder
import androidx.paging.PagedList
import com.yuzo.lib.ui.viewmodel.BaseRefreshViewModel
import com.yuzo.opengit.kotlin.http.service.bean.Repo
import com.yuzo.opengit.kotlin.ui.paging.RepoDataSource
import com.yuzo.opengit.kotlin.ui.paging.RepoDataSourceFactory
import com.yuzo.opengit.kotlin.ui.repository.RepoRepository

/**
 * Author: yuzo
 * Date: 2019-09-26
 */
class RepoViewModel constructor(repository: RepoRepository) :
    BaseRefreshViewModel<Repo, RepoDataSource>() {

    override var lists: LiveData<PagedList<Repo>> =
        LivePagedListBuilder(RepoDataSourceFactory(dataSource, repository), config)
            .build()

    companion object {
        private const val TAG: String = "RepoViewModel"
    }

}