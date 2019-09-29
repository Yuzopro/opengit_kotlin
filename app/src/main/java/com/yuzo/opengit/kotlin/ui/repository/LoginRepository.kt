package com.yuzo.opengit.kotlin.ui.repository

import com.yuzo.lib.http.NetworkScheduler
import com.yuzo.lib.http.ResponseObserver
import com.yuzo.opengit.kotlin.http.HttpClient
import com.yuzo.opengit.kotlin.http.service.bean.LoginRequest
import com.yuzo.opengit.kotlin.http.service.bean.LoginResponse
import com.yuzo.opengit.kotlin.http.service.bean.UserResponse
import com.yuzo.opengit.kotlin.sp.passwordSp
import com.yuzo.opengit.kotlin.sp.accountSp
import com.yuzo.opengit.kotlin.sp.tokenSp

/**
 * Author: yuzo
 * Date: 2019-09-26
 */
class LoginRepository private constructor() {
    fun login(account: String, pwd: String, callback: ResponseObserver<UserResponse>) {
        accountSp = account
        passwordSp = pwd

        HttpClient.getInstance().loginService
            .authorizations(LoginRequest.generate())
            .compose(NetworkScheduler.compose())
            .subscribe(object : ResponseObserver<LoginResponse>() {
                override fun onSuccess(response: LoginResponse?) {
                    response?.apply {
                        tokenSp = this.token
                    }
                    fetchUser(callback)
                }

                override fun onError(code: Int, message: String) {
                    callback.onError(code, message)
                }
            })
    }

    private fun fetchUser(callback: ResponseObserver<UserResponse>) {
        HttpClient.getInstance().userService
            .fetchUserOwner()
            .compose(NetworkScheduler.compose())
            .subscribe(callback)
    }

    companion object {
        @Volatile
        private var instance: LoginRepository? = null

        fun getInstance() = instance ?: synchronized(this) {
            instance ?: LoginRepository().also { instance = it }
        }
    }
}