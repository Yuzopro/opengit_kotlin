package com.yuzo.opengit.kotlin.ui.paging.search

import com.yuzo.lib.ui.paging.AbsDataSourceFactory
import com.yuzo.opengit.kotlin.http.service.bean.Issue
import com.yuzo.opengit.kotlin.http.service.bean.User

/**
 * Author: yuzo
 * Date: 2019-10-09
 */
class SearchUserDataSourceFactory(params : Map<String, Any>) : AbsDataSourceFactory<User, SearchUserDataSource>(params) {
    override fun getDataSource(params : Map<String, Any>): SearchUserDataSource = SearchUserDataSource(params)

    companion object {
        private const val TAG = "IssueDataSourceFactory"
    }

}