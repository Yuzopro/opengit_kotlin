package com.yuzo.opengit.kotlin.ui

import com.yuzo.opengit.kotlin.ui.repository.EventRepository
import com.yuzo.opengit.kotlin.ui.repository.HomeRepository
import com.yuzo.opengit.kotlin.ui.repository.RepoRepository
import com.yuzo.opengit.kotlin.ui.repository.LoginRepository
import com.yuzo.opengit.kotlin.ui.viewmodel.factory.EventViewModelFactory
import com.yuzo.opengit.kotlin.ui.viewmodel.factory.HomeViewModelFactory
import com.yuzo.opengit.kotlin.ui.viewmodel.factory.RepoViewModelFactory
import com.yuzo.opengit.kotlin.ui.viewmodel.factory.LoginViewModelFactory

/**
 * Author: yuzo
 * Date: 2019-09-26
 */
object AppViewModelProvider {
    fun providerLoginModel(): LoginViewModelFactory {
        val repository: LoginRepository = RepositoryProvider.providerLoginRepository()
        return LoginViewModelFactory(repository)
    }

    fun providerHomeModel(): HomeViewModelFactory {
        val repository: HomeRepository = RepositoryProvider.providerHomeRepository()
        return HomeViewModelFactory(repository)
    }

    fun providerRepoModel(): RepoViewModelFactory {
        val repository: RepoRepository = RepositoryProvider.providerRepoRepository()
        return RepoViewModelFactory(repository)
    }

    fun providerEventModel(): EventViewModelFactory {
        val repository: EventRepository = RepositoryProvider.providerEventRepository()
        return EventViewModelFactory(repository)
    }
}