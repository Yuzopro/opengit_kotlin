package com.yuzo.opengit.kotlin.ui.viewmodel.factory

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.yuzo.opengit.kotlin.ui.repository.LoginRepository
import com.yuzo.opengit.kotlin.ui.viewmodel.LoginViewModel

/**
 * Author: yuzo
 * Date: 2019-09-26
 */
class LoginViewModelFactory(private val repository: LoginRepository) :
    ViewModelProvider.NewInstanceFactory() {

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>) = LoginViewModel(repository) as T
}