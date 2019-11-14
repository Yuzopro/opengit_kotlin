package com.yuzo.opengit.kotlin.ui

import com.yuzo.opengit.kotlin.ui.repository.LoginRepository

/**
 * Author: yuzo
 * Date: 2019-09-26
 */
object RepositoryProvider {
    fun providerLoginRepository(): LoginRepository = LoginRepository.getInstance()
}