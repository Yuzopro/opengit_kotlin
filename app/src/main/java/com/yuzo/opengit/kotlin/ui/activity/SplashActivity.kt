package com.yuzo.opengit.kotlin.ui.activity

import com.yuzo.lib.ui.BaseActivity
import com.yuzo.opengit.kotlin.R
import com.yuzo.opengit.kotlin.databinding.ActivitySplashBinding
import kotlinx.android.synthetic.main.activity_splash.*

/**
 * Author: yuzo
 * Date: 2019-09-25
 */
class SplashActivity : BaseActivity<ActivitySplashBinding>() {
    override val layoutId: Int = R.layout.activity_splash

    override fun initContainerView(binding: ActivitySplashBinding) {
        super.initContainerView(binding)

        tv_splash_app_name?.postDelayed({
            LoginActivity.launch(this)
        }, 1000L)
    }
}