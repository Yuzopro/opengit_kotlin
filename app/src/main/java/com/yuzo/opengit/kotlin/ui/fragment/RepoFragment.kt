package com.yuzo.opengit.kotlin.ui.fragment

import androidx.fragment.app.viewModels
import com.yuzo.lib.ui.fragment.BaseRefreshFragment
import com.yuzo.opengit.kotlin.databinding.ListItemRepoBinding
import com.yuzo.opengit.kotlin.http.service.bean.Repo
import com.yuzo.opengit.kotlin.ui.AppViewModelProvider
import com.yuzo.opengit.kotlin.ui.adapter.RepoAdapter
import com.yuzo.opengit.kotlin.ui.adapter.RepoViewHolder
import com.yuzo.opengit.kotlin.ui.paging.RepoDataSource
import com.yuzo.opengit.kotlin.ui.viewmodel.RepoViewModel


/**
 * Author: yuzo
 * Date: 2019-09-30
 */
class RepoFragment :
    BaseRefreshFragment<Repo, ListItemRepoBinding, RepoViewHolder, RepoAdapter, RepoDataSource>() {

    override var mAdapter: RepoAdapter = RepoAdapter()

    override val mViewModel: RepoViewModel by viewModels {
        AppViewModelProvider.providerRepoModel()
    }

    companion object {
        private const val TAG = "RepoFragment"
    }
}