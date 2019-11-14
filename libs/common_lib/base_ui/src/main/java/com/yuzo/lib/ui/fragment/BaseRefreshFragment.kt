package com.yuzo.lib.ui.fragment

import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import com.yuzo.lib.ui.R
import com.yuzo.lib.ui.adapter.BasePagedAdapter
import com.yuzo.lib.ui.databinding.BaseRefreshLayoutBinding
import com.yuzo.lib.ui.repository.NetworkState
import com.yuzo.lib.ui.viewmodel.BaseRefreshViewModel
import kotlinx.android.synthetic.main.base_refresh_layout.*

/**
 * Author: yuzo
 * Date: 2019-10-10
 */
abstract class BaseRefreshFragment<T, A : BasePagedAdapter<T>> : BaseLazyFragment<BaseRefreshLayoutBinding>() {

    abstract var mAdapter: A

    abstract fun getViewModel() : BaseRefreshViewModel<T>

    private lateinit var  mViewModel: BaseRefreshViewModel<T>

    override val layoutId: Int = R.layout.base_refresh_layout

    override fun initData(binding: BaseRefreshLayoutBinding) {
        super.initData(binding)

        mViewModel = getViewModel()

//        mViewModel.lists?.observe(this, Observer {
//            if (it != null) {
//                mAdapter.submitList(it)
//            }
//        })
//
//        mViewModel.loading.observe(this, Observer {
//            it?.let { result ->
//                if (swipe_refresh_layout.isRefreshing) {
//                    hideException()
//                    swipe_refresh_layout.isRefreshing = result.loadingState == LoadingState.Loading
//                } else if (result.loadingState == LoadingState.Loading) {
//                    hideException()
//                    showLoading()
//                } else if (result.loadingState == LoadingState.Failed) {
//                    showException(true)
//                } else if (result.loadingState == LoadingState.Empty) {
//                    showException(false)
//                } else {
//                    hideException()
//                    hideLoading()
//                }
//            }
//        })
    }

    override fun initView() {
        super.initView()

        addFixView(getFixView(cl_fix_layout))

        rv_list.adapter = mAdapter

        mViewModel.lists.observe(this, Observer {
            mAdapter.submitList(it)
        })
        mViewModel.refreshState.observe(this, Observer {
            swipe_refresh_layout.isRefreshing = it == NetworkState.LOADING
        })
        swipe_refresh_layout.setOnRefreshListener {
            mViewModel.refresh()
        }
    }

    override fun onFirstUserVisible() {
        super.onFirstUserVisible()
        mViewModel.doAction(1)
    }

    open fun getFixView(parent: ViewGroup): View? {
        return null
    }

    private fun addFixView(view: View?) {
        if (view != null) {
            cl_fix_layout.addView(view)
            cl_fix_layout.visibility = View.VISIBLE
        } else {
            cl_fix_layout.visibility = View.GONE
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

    companion object {
        private const val TAG = "BaseRefreshFragment"
    }
}
