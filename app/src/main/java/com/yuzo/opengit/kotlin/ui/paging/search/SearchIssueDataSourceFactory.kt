package com.yuzo.opengit.kotlin.ui.paging.search

import com.yuzo.lib.ui.paging.AbsDataSourceFactory
import com.yuzo.opengit.kotlin.http.service.bean.Issue

/**
 * Author: yuzo
 * Date: 2019-10-09
 */
class SearchIssueDataSourceFactory : AbsDataSourceFactory<Issue, SearchIssueDataSource>() {
    override fun getDataSource(): SearchIssueDataSource = SearchIssueDataSource()

    companion object {
        private const val TAG = "IssueDataSourceFactory"
    }

}