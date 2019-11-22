package com.yuzo.opengit.kotlin.ui.repository.search

import com.yuzo.lib.ui.paging.AbsDataSourceFactory
import com.yuzo.lib.ui.repository.AbsRepository
import com.yuzo.opengit.kotlin.http.service.bean.Issue
import com.yuzo.opengit.kotlin.ui.paging.search.SearchIssueDataSource
import com.yuzo.opengit.kotlin.ui.paging.search.SearchIssueDataSourceFactory

/**
 * Author: yuzo
 * Date: 2019-10-09
 */
class SearchIssueRepository : AbsRepository<Issue, SearchIssueDataSource>() {
    override fun getDataSourceFactory(): AbsDataSourceFactory<Issue, SearchIssueDataSource> =
        SearchIssueDataSourceFactory()

    companion object {
        private const val TAG = "IssueRepository"
    }
}