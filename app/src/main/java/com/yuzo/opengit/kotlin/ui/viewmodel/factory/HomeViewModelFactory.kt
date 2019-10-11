package com.yuzo.opengit.kotlin.ui.viewmodel.factory

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.yuzo.opengit.kotlin.ui.repository.HomeRepository
import com.yuzo.opengit.kotlin.ui.repository.RepoRepository
import com.yuzo.opengit.kotlin.ui.viewmodel.HomeViewModel
import com.yuzo.opengit.kotlin.ui.viewmodel.RepoViewModel

/**
 * Author: yuzo
 * Date: 2019-09-26
 */
class HomeViewModelFactory(private val repository: HomeRepository) :
    ViewModelProvider.NewInstanceFactory() {

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>) = HomeViewModel(repository) as T
}