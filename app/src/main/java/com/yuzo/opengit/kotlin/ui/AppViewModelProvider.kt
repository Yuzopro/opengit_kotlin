package com.yuzo.opengit.kotlin.ui

import com.yuzo.opengit.kotlin.ui.repository.LoginRepository
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
}