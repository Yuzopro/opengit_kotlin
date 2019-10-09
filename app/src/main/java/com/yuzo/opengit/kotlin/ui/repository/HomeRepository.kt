package com.yuzo.opengit.kotlin.ui.repository

/**
 * Author: yuzo
 * Date: 2019-10-09
 */
class HomeRepository private constructor() {
    companion object {
        @Volatile
        private var instance: HomeRepository? = null

        fun getInstance() = instance ?: synchronized(this) {
            instance ?: HomeRepository().also { instance = it }
        }
    }
}