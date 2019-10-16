package com.yuzo.opengit.kotlin.ui.paging

import androidx.lifecycle.MutableLiveData
import androidx.paging.DataSource
import com.yuzo.lib.log.v
import com.yuzo.opengit.kotlin.http.service.bean.Issue
import com.yuzo.opengit.kotlin.ui.repository.IssueRepository

/**
 * Author: yuzo
 * Date: 2019-10-09
 */
class IssueDataSourceFactory constructor(
    private val dataSource: MutableLiveData<IssueDataSource>,
    private val repository: IssueRepository,
    private var filter: String,
    private var state: String,
    private var sort: String,
    private var direction: String
) : DataSource.Factory<Int, Issue>() {

    override fun create(): DataSource<Int, Issue> {
        return IssueDataSource(repository, filter, state, sort, direction).apply {
            dataSource.postValue(
                this
            )
        }
    }

    fun changeSearchState(filter: String, state: String, sort: String, direction: String) {
        this.filter = filter
        this.state = state
        this.sort = sort
        this.direction = direction
    }

}