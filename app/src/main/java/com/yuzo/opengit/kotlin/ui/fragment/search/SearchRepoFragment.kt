package com.yuzo.opengit.kotlin.ui.fragment.search

import android.os.Bundle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import com.yuzo.lib.ui.fragment.BaseWebFragment
import com.yuzo.opengit.kotlin.R
import com.yuzo.opengit.kotlin.http.service.bean.Repo
import com.yuzo.opengit.kotlin.ui.adapter.RepoAdapter
import com.yuzo.opengit.kotlin.ui.adapter.RepoViewHolder
import com.yuzo.opengit.kotlin.ui.repository.search.SearchRepoRepository
import com.yuzo.opengit.kotlin.ui.viewmodel.search.SearchRepoViewModel

/**
 * Author: yuzo
 * Date: 2019-11-22
 */
class SearchRepoFragment : SearchItemFragment<Repo, RepoViewHolder, RepoAdapter, SearchRepoViewModel>() {
    override var mAdapter: RepoAdapter  = RepoAdapter()

    override fun getViewModel(): SearchRepoViewModel {
        return ViewModelProviders.of(this, object : ViewModelProvider.Factory {
            override fun <T : ViewModel?> create(modelClass: Class<T>): T {
                return SearchRepoViewModel(SearchRepoRepository()) as T
            }
        })[SearchRepoViewModel::class.java]
    }

    override fun OnItemClick(item: Repo?, position: Int) {
        val bundle = Bundle()
        bundle.putString(BaseWebFragment.KEY_URL, item?.html_url!!)
        nav().navigate(R.id.action_search_to_web, bundle)
    }

}