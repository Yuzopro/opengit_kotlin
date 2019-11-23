package com.yuzo.opengit.kotlin.ui.paging

import com.yuzo.lib.ui.paging.AbsDataSourceFactory
import com.yuzo.opengit.kotlin.http.service.bean.Issue

/**
 * Author: yuzo
 * Date: 2019-10-09
 */
class IssueDataSourceFactory(params : Map<String, Any>) : AbsDataSourceFactory<Issue, IssueDataSource>(params) {
    override fun getDataSource(params : Map<String, Any>): IssueDataSource = IssueDataSource(params)

    companion object {
        private const val TAG = "IssueDataSourceFactory"
    }

}