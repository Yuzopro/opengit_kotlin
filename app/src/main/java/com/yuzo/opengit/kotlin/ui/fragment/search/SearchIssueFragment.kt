package com.yuzo.opengit.kotlin.ui.fragment.search

import android.os.Bundle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import com.yuzo.lib.ui.fragment.BaseWebFragment
import com.yuzo.opengit.kotlin.R
import com.yuzo.opengit.kotlin.http.service.bean.Issue
import com.yuzo.opengit.kotlin.ui.adapter.IssueAdapter
import com.yuzo.opengit.kotlin.ui.repository.search.SearchIssueRepository
import com.yuzo.opengit.kotlin.ui.viewmodel.search.SearchIssueViewModel

/**
 * Author: yuzo
 * Date: 2019-10-12
 */
class SearchIssueFragment : SearchItemFragment<Issue, IssueAdapter, SearchIssueViewModel>() {
    override var mAdapter: IssueAdapter = IssueAdapter()

    override fun getViewModel(): SearchIssueViewModel {
        return ViewModelProviders.of(this, object : ViewModelProvider.Factory {
            override fun <T : ViewModel?> create(modelClass: Class<T>): T {
                return SearchIssueViewModel(SearchIssueRepository()) as T
            }
        })[SearchIssueViewModel::class.java]
    }

    override fun OnItemClick(item: Issue?, position: Int) {
        val bundle = Bundle()
        bundle.putString(BaseWebFragment.KEY_URL, item?.html_url!!)
        nav().navigate(R.id.action_search_to_web, bundle)
    }

    companion object {
        private const val TAG = "SearchIssueFragment"

    }
}