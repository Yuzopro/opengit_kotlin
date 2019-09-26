package com.yuzo.opengit.kotlin.ui.viewmodel

import android.text.Editable
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.yuzo.lib.tool.ToastUtil
import com.yuzo.opengit.kotlin.R
import com.yuzo.opengit.kotlin.common.SimpleTextWatcher
import com.yuzo.opengit.kotlin.ui.repository.LoginRepository

/**
 * Author: yuzo
 * Date: 2019-09-26
 */
class LoginViewModel constructor(private val repository: LoginRepository) : ViewModel() {
    companion object {
        private const val TAG: String = "LoginViewModel"
    }

    val account = MutableLiveData<String>("")
    val password = MutableLiveData<String>("")

    val accountWatcher = object : SimpleTextWatcher() {
        override fun afterTextChanged(s: Editable) {
            super.afterTextChanged(s)

            account.value = s.toString()
        }
    }

    val passwordWatcher = object : SimpleTextWatcher() {
        override fun afterTextChanged(s: Editable) {
            super.afterTextChanged(s)

            password.value = s.toString()
        }
    }

    fun onLogin() {
        when (account.value.isNullOrEmpty() || password.value.isNullOrEmpty()) {
            true -> {
                ToastUtil.showShort(R.string.login_text_account_or_password_null)
            }
            false -> {
                ToastUtil.showShort("onLogin")
            }
        }
    }

    fun onSignUp() {
        ToastUtil.showShort("onSignUp")
    }
}