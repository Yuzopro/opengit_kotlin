package com.yuzo.opengit.kotlin.ui.fragment

import android.os.Bundle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import com.yuzo.lib.ui.adapter.BasePagedAdapter
import com.yuzo.lib.ui.fragment.BaseRefreshFragment
import com.yuzo.lib.ui.fragment.BaseWebFragment
import com.yuzo.opengit.kotlin.R
import com.yuzo.opengit.kotlin.http.service.bean.Repo
import com.yuzo.opengit.kotlin.ui.adapter.RepoAdapter
import com.yuzo.opengit.kotlin.ui.repository.RepoRepository
import com.yuzo.opengit.kotlin.ui.viewmodel.RepoViewModel


/**
 * Author: yuzo
 * Date: 2019-09-30
 */
class RepoFragment : BaseRefreshFragment<Repo, RepoAdapter, RepoViewModel>(),
    BasePagedAdapter.OnItemClickListener<Repo> {

    override var mAdapter: RepoAdapter = RepoAdapter()

    override fun initView() {
        super.initView()

        mAdapter.listener = this
    }

    override fun onDestroyView() {
        super.onDestroyView()

        mAdapter.listener = null
    }

    override fun getViewModel(): RepoViewModel {
        return ViewModelProviders.of(this, object : ViewModelProvider.Factory {
            override fun <T : ViewModel?> create(modelClass: Class<T>): T {
                return RepoViewModel(RepoRepository()) as T
            }
        })[RepoViewModel::class.java]
    }

    override fun OnItemClick(item: Repo?, position: Int) {
        val bundle = Bundle()
        bundle.putString(BaseWebFragment.KEY_URL, item?.html_url!!)
        nav().navigate(R.id.action_to_web, bundle)
    }

    companion object {
        private const val TAG = "RepoFragment"
    }
}