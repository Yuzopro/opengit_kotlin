package com.yuzo.lib.ui

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.fragment.app.Fragment

/**
 * Author: yuzo
 * Date: 2019-09-25
 */
abstract class BaseFragment<T : ViewDataBinding> : Fragment() {
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

    open fun initView() {}

    open fun initData(binding: T) {}
}