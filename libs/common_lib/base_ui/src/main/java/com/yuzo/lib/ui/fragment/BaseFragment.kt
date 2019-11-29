package com.yuzo.lib.ui.fragment

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.fragment.app.Fragment
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import com.yuzo.lib.ui.view.LoadingDialog

/**
 * Author: yuzo
 * Date: 2019-09-25
 */
abstract class BaseFragment<T : ViewDataBinding> : Fragment() {
    private var mLoadingDialog: LoadingDialog? = null

    abstract val layoutId: Int

    open var mContext: Context? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        mContext = activity

        val binding: T = DataBindingUtil.inflate(
            inflater
            , layoutId
            , container
            , false
        )

        initData(binding)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initView()
    }

    open fun initView() {
        mContext?.apply {
            mLoadingDialog = LoadingDialog(this)
        }
    }

    open fun initData(binding: T) {}

    protected fun nav(): NavController {
        return NavHostFragment.findNavController(this)
    }

    protected fun pop() {
        nav().popBackStack()
    }

    open fun showLoading() {
        activity?.apply {
            if (!isFinishing) {
                mLoadingDialog?.show()
            }
        }
    }

    open fun hideLoading() {
        activity?.apply {
            if (!isFinishing) {
                mLoadingDialog?.dismiss()
            }
        }
    }
}