package com.yuzo.opengit.kotlin.ui.repository

import com.yuzo.lib.http.NetworkScheduler
import com.yuzo.lib.http.ResponseObserver
import com.yuzo.opengit.kotlin.http.HttpClient
import com.yuzo.opengit.kotlin.http.service.bean.LoginRequest
import com.yuzo.opengit.kotlin.http.service.bean.Login
import com.yuzo.opengit.kotlin.http.service.bean.User
import com.yuzo.opengit.kotlin.sp.passwordSp
import com.yuzo.opengit.kotlin.sp.accountSp
import com.yuzo.opengit.kotlin.sp.tokenSp

/**
 * Author: yuzo
 * Date: 2019-09-26
 */
class LoginRepository private constructor() {
    fun login(account: String, pwd: String, callback: ResponseObserver<User>) {
        accountSp = account
        passwordSp = pwd

        HttpClient.getInstance().loginService
            .authorizations(LoginRequest.generate())
            .compose(NetworkScheduler.compose())
            .subscribe(object : ResponseObserver<Login>() {
                override fun onSuccess(response: Login?) {
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

    private fun fetchUser(callback: ResponseObserver<User>) {
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