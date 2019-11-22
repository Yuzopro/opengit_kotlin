package com.yuzo.opengit.kotlin.ui.paging.search

import com.yuzo.lib.ui.paging.AbsDataSourceFactory
import com.yuzo.opengit.kotlin.http.service.bean.Issue
import com.yuzo.opengit.kotlin.http.service.bean.User

/**
 * Author: yuzo
 * Date: 2019-10-09
 */
class SearchUserDataSourceFactory : AbsDataSourceFactory<User, SearchUserDataSource>() {
    override fun getDataSource(): SearchUserDataSource = SearchUserDataSource()

    companion object {
        private const val TAG = "IssueDataSourceFactory"
    }

}