package com.yuzo.lib.ui.fragment

import android.view.View
import androidx.databinding.ViewDataBinding
import androidx.lifecycle.Observer
import com.yuzo.lib.ui.R
import com.yuzo.lib.ui.adapter.BasePagedListAdapter
import com.yuzo.lib.ui.adapter.BaseViewHolder
import com.yuzo.lib.ui.databinding.BaseRefreshLayoutBinding
import com.yuzo.lib.ui.paging.BasePositionalDataSource
import com.yuzo.lib.ui.paging.LoadingState
import com.yuzo.lib.ui.viewmodel.BaseRefreshViewModel
import kotlinx.android.synthetic.main.base_refresh_layout.*

/**
 * Author: yuzo
 * Date: 2019-10-10
 */
abstract class BaseRefreshFragment<T, B : ViewDataBinding, VH : BaseViewHolder<T, B>, A : BasePagedListAdapter<T, B, VH>, P : BasePositionalDataSource<T>> :
    BaseFragment<BaseRefreshLayoutBinding>() {

    abstract var mAdapter: A

    abstract val mViewModel: BaseRefreshViewModel<T, P>

    override val layoutId: Int = R.layout.base_refresh_layout

    override fun initData(binding: BaseRefreshLayoutBinding) {
        super.initData(binding)

        mViewModel.lists.observe(this, Observer {
            if (it != null) {
                mAdapter.submitList(it)
            }
        })

        mViewModel.loading.observe(this, Observer {
            it?.let { result ->
                if (swipe_refresh_layout.isRefreshing) {
                    hideException()
                    swipe_refresh_layout.isRefreshing = result.loadingState == LoadingState.Loading
                } else if (result.loadingState == LoadingState.Loading) {
                    hideException()
                    showLoading()
                } else if (result.loadingState == LoadingState.Failed) {
                    showException(true)
                } else if (result.loadingState == LoadingState.Empty) {
                    showException(false)
                } else {
                    hideException()
                    hideLoading()
                }
            }
        })
    }

    override fun initView() {
        super.initView()

        rv_list.adapter = mAdapter

        swipe_refresh_layout.setOnRefreshListener {
            mViewModel.onRefresh()
        }
    }

    private fun showException(isError: Boolean) {
        cl_exception?.visibility = View.VISIBLE
        if (isError) {
            iv_base_refresh_error?.setImageResource(R.drawable.ic_network_error)
            tv_base_refresh_error?.text = getString(R.string.text_network_error)
        } else {
            iv_base_refresh_error?.setImageResource(R.drawable.ic_no_data)
            tv_base_refresh_error?.text = getString(R.string.text_empty_data)
        }
        hideLoading()
    }

    private fun hideException() {
        cl_exception?.visibility = View.GONE
    }

    companion object{
        private const val TAG = "BaseRefreshFragment"
    }
}
