package com.yuzo.opengit.kotlin.ui.fragment

import android.content.Intent
import android.net.Uri
import androidx.fragment.app.viewModels
import com.yuzo.lib.ui.adapter.BasePagedAdapter
import com.yuzo.lib.ui.fragment.BaseRefreshFragment
import com.yuzo.opengit.kotlin.databinding.ListItemRepoBinding
import com.yuzo.opengit.kotlin.http.service.bean.Repo
import com.yuzo.opengit.kotlin.ui.AppViewModelProvider
import com.yuzo.opengit.kotlin.ui.adapter.RepoAdapter
import com.yuzo.opengit.kotlin.ui.paging.RepoDataSource
import com.yuzo.opengit.kotlin.ui.viewmodel.RepoViewModel


/**
 * Author: yuzo
 * Date: 2019-09-30
 */
class RepoFragment : BaseRefreshFragment<Repo, RepoAdapter, RepoDataSource>(),
    BasePagedAdapter.OnItemClickListener<Repo> {

    override var mAdapter: RepoAdapter = RepoAdapter()

    override val mViewModel: RepoViewModel by viewModels {
        AppViewModelProvider.providerRepoModel()
    }

    override fun initView() {
        super.initView()

        mAdapter.listener = this
    }

    override fun onDestroyView() {
        super.onDestroyView()

        mAdapter.listener = null
    }

    override fun OnItemClick(item: Repo?, position: Int) {
        val uri = Uri.parse(item?.url)
        val intent = Intent(Intent.ACTION_VIEW, uri)
        startActivity(intent)
    }

    companion object {
        private const val TAG = "RepoFragment"
    }
}