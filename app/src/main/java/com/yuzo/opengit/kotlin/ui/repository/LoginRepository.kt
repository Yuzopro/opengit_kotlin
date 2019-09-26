package com.yuzo.opengit.kotlin.ui.repository

/**
 * Author: yuzo
 * Date: 2019-09-26
 */
class LoginRepository private constructor() {
    companion object {
        @Volatile
        private var instance: LoginRepository? = null

        fun getInstance() = instance ?: synchronized(this) {
            instance ?: LoginRepository().also { instance = it }
        }
    }
}