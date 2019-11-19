package com.yuzo.opengit.kotlin.ui.paging

import com.yuzo.lib.ui.paging.AbsDataSourceFactory
import com.yuzo.opengit.kotlin.http.service.bean.Issue

/**
 * Author: yuzo
 * Date: 2019-10-09
 */
class IssueDataSourceFactory : AbsDataSourceFactory<Issue, IssueDataSource>() {
    override fun getDataSource(): IssueDataSource = IssueDataSource()

    companion object {
        private const val TAG = "IssueDataSourceFactory"
    }

}