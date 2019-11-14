package com.yuzo.opengit.kotlin.ui.paging

import com.yuzo.lib.ui.paging.AbsDataSourceFactory
import com.yuzo.opengit.kotlin.http.service.bean.Issue

/**
 * Author: yuzo
 * Date: 2019-10-09
 */
class IssueDataSourceFactory(
    var filter: String,
    var state: String,
    var sort: String,
    var direction: String
) : AbsDataSourceFactory<Issue, IssueDataSource>() {
    override fun getDataSource(): IssueDataSource = IssueDataSource(filter, state, sort, direction)

    companion object {
        private const val TAG = "IssueDataSourceFactory"
    }

}