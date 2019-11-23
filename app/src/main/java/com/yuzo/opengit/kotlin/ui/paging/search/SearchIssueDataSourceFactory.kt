package com.yuzo.opengit.kotlin.ui.paging.search

import com.yuzo.lib.ui.paging.AbsDataSourceFactory
import com.yuzo.opengit.kotlin.http.service.bean.Issue

/**
 * Author: yuzo
 * Date: 2019-10-09
 */
class SearchIssueDataSourceFactory(params : Map<String, Any>) : AbsDataSourceFactory<Issue, SearchIssueDataSource>(params) {
    override fun getDataSource(params : Map<String, Any>): SearchIssueDataSource = SearchIssueDataSource(params)

    companion object {
        private const val TAG = "IssueDataSourceFactory"
    }

}