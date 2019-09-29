package com.yuzo.opengit.kotlin.ui.viewmodel

import android.text.Editable
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.google.gson.Gson
import com.yuzo.lib.http.ResponseObserver
import com.yuzo.lib.tool.ToastUtil
import com.yuzo.lib.tool.ToastUtil.showShort
import com.yuzo.opengit.kotlin.R
import com.yuzo.opengit.kotlin.common.SimpleTextWatcher
import com.yuzo.opengit.kotlin.http.service.bean.UserResponse
import com.yuzo.opengit.kotlin.sp.userSp
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
    val loading = MutableLiveData<Boolean>(false)
    val user = MutableLiveData<UserResponse>(null)

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
                showShort(R.string.login_text_account_or_password_null)
            }
            false -> {
                loading.value = true

                val account = account.value!!
                val password = password.value!!

                repository.login(account, password, object : ResponseObserver<UserResponse>() {
                    override fun onSuccess(response: UserResponse?) {
                        loading.value = false

                        userSp = Gson().toJson(response)

                        user.value = response
                    }

                    override fun onError(code: Int, message: String) {
                        loading.value = false

                        showShort(message)
                    }
                })
            }
        }
    }

    fun onSignUp() {
        showShort("onSignUp")
    }
}