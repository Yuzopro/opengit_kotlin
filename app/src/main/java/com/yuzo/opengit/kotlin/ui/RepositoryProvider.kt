package com.yuzo.opengit.kotlin.ui

import com.yuzo.opengit.kotlin.ui.repository.*

/**
 * Author: yuzo
 * Date: 2019-09-26
 */
object RepositoryProvider {
    fun providerLoginRepository(): LoginRepository = LoginRepository.getInstance()

    fun providerHomeRepository(): HomeRepository = HomeRepository.getInstance()

    fun providerRepoRepository(): RepoRepository = RepoRepository.getInstance()

    fun providerEventRepository(): EventRepository = EventRepository.getInstance()

    fun providerIssueRepository(): IssueRepository = IssueRepository.getInstance()
}