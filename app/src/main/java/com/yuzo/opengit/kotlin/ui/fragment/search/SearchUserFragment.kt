package com.yuzo.opengit.kotlin.ui.fragment.search

import android.os.Bundle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import com.yuzo.lib.ui.fragment.BaseWebFragment
import com.yuzo.opengit.kotlin.R
import com.yuzo.opengit.kotlin.http.service.bean.User
import com.yuzo.opengit.kotlin.ui.adapter.UserAdapter
import com.yuzo.opengit.kotlin.ui.adapter.UserViewHolder
import com.yuzo.opengit.kotlin.ui.repository.search.SearchUserRepository
import com.yuzo.opengit.kotlin.ui.viewmodel.search.SearchUserViewModel

/**
 * Author: yuzo
 * Date: 2019-10-12
 */
class SearchUserFragment :
    SearchItemFragment<User, UserViewHolder, UserAdapter, SearchUserViewModel>() {

    override var mAdapter: UserAdapter = UserAdapter()

    override fun getViewModel(): SearchUserViewModel {
        return ViewModelProviders.of(this, object : ViewModelProvider.Factory {
            override fun <T : ViewModel?> create(modelClass: Class<T>): T {
                return SearchUserViewModel(SearchUserRepository()) as T
            }
        })[SearchUserViewModel::class.java]
    }

    override fun OnItemClick(item: User?, position: Int) {
        val bundle = Bundle()
        bundle.putString(BaseWebFragment.KEY_URL, item?.htmlUrl!!)
        nav().navigate(R.id.action_search_to_web, bundle)
    }

    companion object {
        private const val TAG = "SearchIssueFragment"

    }
}