package com.yuzo.lib.ui.fragment

import android.os.Bundle
import android.util.Log.v
import androidx.databinding.ViewDataBinding

/**
 * Author: yuzo
 * Date: 2019-11-13
 */
abstract class BaseLazyFragment<T : ViewDataBinding> : BaseFragment<T>() {
    //第一次onresume
    private var isFirstResume = true
    //第一次展示
    private var isFirstVisible = true
    //第一次隐藏
    private var isFirstInvisible = true
    private var isPrepared: Boolean = false

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        v(this.javaClass.simpleName, "onActivityCreated")
        initPrepare()
    }

    override fun onResume() {
        super.onResume()

        v(this.javaClass.simpleName, "onResume isFirstResume is $isFirstResume, userVisibleHint is $userVisibleHint")

        if (isFirstResume) {
            isFirstResume = false
            return
        }

        if (userVisibleHint) {
            onUserVisible()
        }
    }

    override fun onPause() {
        super.onPause()

        v(this.javaClass.simpleName, "onPause userVisibleHint is $userVisibleHint")

        if (userVisibleHint) {
            onUserInvisible()
        }
    }

    override fun setUserVisibleHint(isVisibleToUser: Boolean) {
        super.setUserVisibleHint(isVisibleToUser)

        v(this.javaClass.simpleName, "setUserVisibleHint isVisibleToUser is $isVisibleToUser")


        if (isVisibleToUser) {
            if (isFirstVisible) {
                isFirstVisible = false
                initPrepare()
            } else {
                onUserVisible()
            }
        } else {
            if (isFirstInvisible) {
                isFirstInvisible = false
                onFirstUserInVisible()
            } else {
                onUserInvisible()
            }
        }
    }

    private fun initPrepare() {
        v(this.javaClass.simpleName, "initPrepare isPrepared is $isPrepared")

        if (isPrepared) {
            onFirstUserVisible()
        } else {
            isPrepared = true
        }
    }

    /**用户触发fragment第一次展示，为了加载数据 */
    open fun onFirstUserVisible() {
        v(this.javaClass.simpleName, "onFirstUserVisible")
    }

    /**用户触发fragment展示 */
    open fun onUserVisible() {
        v(this.javaClass.simpleName, "onUserVisible")
    }

    /**用户触发fragment第一次隐藏 */
    open fun onFirstUserInVisible() {
        v(this.javaClass.simpleName, "onFirstUserInVisible")
    }

    /**用户触发fragment隐藏 */
    open fun onUserInvisible() {
        v(this.javaClass.simpleName, "onUserInvisible")
    }

    companion object {
        private const val TAG = "BaseLazyFragment"
    }
}