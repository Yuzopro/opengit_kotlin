package com.yuzo.opengit.kotlin.ui.repository.search

import com.yuzo.lib.ui.paging.AbsDataSourceFactory
import com.yuzo.lib.ui.repository.AbsRepository
import com.yuzo.opengit.kotlin.http.service.bean.Issue
import com.yuzo.opengit.kotlin.http.service.bean.User
import com.yuzo.opengit.kotlin.ui.paging.search.SearchIssueDataSource
import com.yuzo.opengit.kotlin.ui.paging.search.SearchIssueDataSourceFactory
import com.yuzo.opengit.kotlin.ui.paging.search.SearchUserDataSource
import com.yuzo.opengit.kotlin.ui.paging.search.SearchUserDataSourceFactory

/**
 * Author: yuzo
 * Date: 2019-10-09
 */
class SearchUserRepository : AbsRepository<User, SearchUserDataSource>() {
    override fun getDataSourceFactory(): AbsDataSourceFactory<User, SearchUserDataSource> =
        SearchUserDataSourceFactory()

    companion object {
        private const val TAG = "IssueRepository"
    }
}