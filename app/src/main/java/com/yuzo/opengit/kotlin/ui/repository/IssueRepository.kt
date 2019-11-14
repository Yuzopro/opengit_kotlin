package com.yuzo.opengit.kotlin.ui.repository

import com.yuzo.lib.ui.paging.AbsDataSourceFactory
import com.yuzo.lib.ui.repository.AbsRepository
import com.yuzo.opengit.kotlin.http.service.bean.Issue
import com.yuzo.opengit.kotlin.ui.paging.IssueDataSource
import com.yuzo.opengit.kotlin.ui.paging.IssueDataSourceFactory

/**
 * Author: yuzo
 * Date: 2019-10-09
 */
class IssueRepository(
    var filter: String,
    var state: String,
    var sort: String,
    var direction: String
) : AbsRepository<Issue, IssueDataSource>() {
    override fun getDataSourceFactory(): AbsDataSourceFactory<Issue, IssueDataSource> =
        IssueDataSourceFactory(filter, state, sort, direction)

    fun changeSearchState(filter: String, state: String, sort: String, direction: String) {
//        this.filter = filter
//        this.state = state
//        this.sort = sort
//        this.direction = direction
    }

    companion object {
        private const val TAG = "IssueRepository"
    }
}