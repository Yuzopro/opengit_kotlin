package com.yuzo.opengit.kotlin.ui.fragment.search

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import com.yuzo.opengit.kotlin.http.service.bean.Repo
import com.yuzo.opengit.kotlin.ui.adapter.RepoAdapter
import com.yuzo.opengit.kotlin.ui.repository.search.SearchRepoRepository
import com.yuzo.opengit.kotlin.ui.viewmodel.search.SearchRepoViewModel

/**
 * Author: yuzo
 * Date: 2019-11-22
 */
class SearchRepoFragment : SearchItemFragment<Repo, RepoAdapter, SearchRepoViewModel>() {
    override var mAdapter: RepoAdapter  = RepoAdapter()

    override fun getViewModel(): SearchRepoViewModel {
        return ViewModelProviders.of(this, object : ViewModelProvider.Factory {
            override fun <T : ViewModel?> create(modelClass: Class<T>): T {
                return SearchRepoViewModel(SearchRepoRepository()) as T
            }
        })[SearchRepoViewModel::class.java]
    }

}