package com.yuzo.opengit.kotlin.ui.viewmodel.factory

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.yuzo.opengit.kotlin.ui.repository.RepoRepository
import com.yuzo.opengit.kotlin.ui.viewmodel.RepoViewModel

/**
 * Author: yuzo
 * Date: 2019-09-26
 */
class RepoViewModelFactory(private val repository: RepoRepository) :
    ViewModelProvider.NewInstanceFactory() {

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>) = RepoViewModel(repository) as T
}