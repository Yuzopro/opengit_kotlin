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
class IssueRepository : AbsRepository<Issue, IssueDataSource>() {
    override fun getDataSourceFactory(params : Map<String, Any>): AbsDataSourceFactory<Issue, IssueDataSource> =
        IssueDataSourceFactory(params)

    companion object {
        private const val TAG = "IssueRepository"
    }
}