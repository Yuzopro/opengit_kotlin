package com.yuzo.opengit.kotlin.ui.paging

import androidx.lifecycle.MutableLiveData
import androidx.paging.DataSource
import com.yuzo.opengit.kotlin.http.service.bean.Repo
import com.yuzo.opengit.kotlin.ui.repository.RepoRepository

/**
 * Author: yuzo
 * Date: 2019-10-09
 */
class RepoDataSourceFactory constructor(
    private val dataSource: MutableLiveData<RepoDataSource>,
    private val repository: RepoRepository
) :
    DataSource.Factory<Int, Repo>() {
    override fun create(): DataSource<Int, Repo> =
        RepoDataSource(repository).apply { dataSource.postValue(this) }
}