package com.yuzo.lib.ui.fragment

import android.util.Log
import android.util.Log.v
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import com.yuzo.lib.tool.ToastUtil
import com.yuzo.lib.ui.R
import com.yuzo.lib.ui.adapter.BasePagedAdapter
import com.yuzo.lib.ui.databinding.BaseRefreshLayoutBinding
import com.yuzo.lib.ui.repository.NetworkState
import com.yuzo.lib.ui.repository.Status
import com.yuzo.lib.ui.viewmodel.BaseRefreshViewModel
import kotlinx.android.synthetic.main.base_refresh_layout.*

/**
 * Author: yuzo
 * Date: 2019-10-10
 */
abstract class BaseRefreshFragment<T, A : BasePagedAdapter<T>, V : BaseRefreshViewModel<T>> :
    BaseLazyFragment<BaseRefreshLayoutBinding>() {

    abstract var mAdapter: A

    abstract fun getViewModel(): V

    lateinit var mViewModel: V

    override val layoutId: Int = R.layout.base_refresh_layout

    override fun initData(binding: BaseRefreshLayoutBinding) {
        super.initData(binding)

        mViewModel = getViewModel()

        mViewModel.lists.observe(this, Observer {
            mAdapter.submitList(it)
        })
        mViewModel.networkState.observe(this, Observer {
            swipe_refresh_layout.isRefreshing = it == NetworkState.LOADING

            if (it.status == Status.LOAD_FAILED) {
                ToastUtil.showShort(it.msg ?: getString(R.string.text_network_error))
            } else if (it.status == Status.FAILED) {
                showException(true)
                ToastUtil.showShort(it.msg ?: getString(R.string.text_network_error))
            } else if (it.status == Status.NO_DATA) {
                showException(false)
            } else {
                hideException()
            }
        })

    }

    override fun initView() {
        super.initView()

        addFixView(getFixView(cl_fix_layout))

        rv_list.adapter = mAdapter

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
            cl_exception?.setOnClickListener {
                mViewModel.retry()
            }
        } else {
            iv_base_refresh_error?.setImageResource(R.drawable.ic_no_data)
            tv_base_refresh_error?.text = getString(R.string.text_empty_data)
            cl_exception?.setOnClickListener(null)
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
