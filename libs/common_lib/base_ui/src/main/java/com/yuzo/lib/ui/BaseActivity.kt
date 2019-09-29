package com.yuzo.lib.ui

import android.content.Context
import android.os.Build
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import com.yuzo.lib.tool.statusBarLightMode
import com.yuzo.lib.ui.util.setColorNoTranslucent
import kotlinx.android.synthetic.main.activity_base_layout.*

/**
 * Author: yuzo
 * Date: 2019-09-25
 */
abstract class BaseActivity<T : ViewDataBinding> : AppCompatActivity(), View.OnClickListener {
    open var mContext: Context? = null

    protected abstract val layoutId: Int

    private var mLoadingDialog: LoadingDialog? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        lightStatusBar(enableLightStatusBar())

        val binding = DataBindingUtil.setContentView<T>(this, layoutId)

        if (true) {
            setColorNoTranslucent(this, resources.getColor(getStatusBarColor()))
        } else {
            immerse()
        }

        mContext = this

        initData(binding)
        initView(binding)
    }

    open fun enableLightStatusBar() = true

    open fun initData(binding: T) {}

    private fun initView(binding: T) {
        mLoadingDialog = LoadingDialog(this)

        initHeaderView()
        initContainerView(binding)
    }

    private fun initHeaderView() {
        tv_header_left?.setOnClickListener(this)
        tv_header_right?.setOnClickListener(this)
    }

    open fun setHeaderCenter(text: String) {
        tv_header_center?.setText(text)
    }

    open fun setHeaderCenter(id: Int) {
        tv_header_center?.setText(id)
    }

    open fun initContainerView(binding: T) {}

    override fun onClick(v: View?) {
        when (v?.id) {
            R.id.tv_header_left -> {
                onClickHeaderLeft()
            }
            R.id.tv_header_right -> {
                onClickHeaderRight()
            }
        }
    }

    open fun onClickHeaderLeft() {
        if (!isFinishing) {
            finish()
        }
    }

    open fun onClickHeaderRight() {}

    open fun showLoading() {
        if (!isFinishing) {
            mLoadingDialog?.show()
        }
    }

    open fun hideLoading() {
        if (!isFinishing) {
            mLoadingDialog?.dismiss()
        }
    }

    private fun lightStatusBar(light: Boolean) {
        if (light && Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            val result = statusBarLightMode(this)
        }
    }

    private fun immerse() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            window.statusBarColor = 0
        }
        val decorView = window.decorView
        val flags = decorView.systemUiVisibility
        decorView.systemUiVisibility = flags or View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
    }

    protected fun getStatusBarColor(): Int {
        return R.color.white
    }

}