package com.yuzo.opengit.kotlin.ui.activity

import android.content.Intent
import android.text.SpannableString
import android.text.style.UnderlineSpan
import androidx.fragment.app.FragmentActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import com.yuzo.lib.tool.getScreenHeight
import com.yuzo.lib.ui.activity.BaseActivity
import com.yuzo.opengit.kotlin.R
import com.yuzo.opengit.kotlin.databinding.ActivityLoginBinding
import com.yuzo.opengit.kotlin.ui.repository.LoginRepository
import com.yuzo.opengit.kotlin.ui.viewmodel.LoginViewModel
import kotlinx.android.synthetic.main.activity_login.*

/**
 * Author: yuzo
 * Date: 2019-09-25
 */
class LoginActivity : BaseActivity<ActivityLoginBinding>() {
    override val layoutId: Int = R.layout.activity_login

    private var loginViewModel: LoginViewModel? = null

    companion object {
        fun launch(activity: FragmentActivity) =
            activity.apply {
                startActivity(Intent(this, LoginActivity::class.java))
                finish()
            }
    }

    override fun initData(binding: ActivityLoginBinding) {
        super.initData(binding)

        loginViewModel = ViewModelProviders.of(this, object : ViewModelProvider.Factory {
            override fun <T : ViewModel?> create(modelClass: Class<T>): T {
                return LoginViewModel(LoginRepository.getInstance()) as T
            }
        })[LoginViewModel::class.java]

        binding.model = loginViewModel
    }

    override fun initContainerView(binding: ActivityLoginBinding) {
        super.initContainerView(binding)

        val screenHeight = getScreenHeight(this)
        iv_login_icon?.layoutParams?.width = screenHeight / 7
        iv_login_icon?.layoutParams?.height = screenHeight / 7

        fl_input_container?.layoutParams?.height = (screenHeight / 3.8).toInt()

        val text = getString(R.string.login_text_sign_up)
        val spannable = SpannableString(text)
        spannable.setSpan(UnderlineSpan(), 0, text.length, 0)
        tv_login_sign_up?.text = spannable

        loginViewModel?.loading?.observe(this, Observer {
            if (it) {
                showLoading()
            } else {
                hideLoading()
            }
        })

        loginViewModel?.user?.observe(this, Observer {
            if (it != null) {
                MainActivity.launch(this)
            }
        })
    }
}