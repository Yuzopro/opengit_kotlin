package com.yuzo.opengit.kotlin

import androidx.multidex.MultiDexApplication
import com.yuzo.lib.tool.ToastUtil
import com.yuzo.opengit.kotlin.http.HttpClient
import com.yuzo.opengit.kotlin.http.HttpClient2

/**
 * Author: yuzo
 * Date: 2019-09-25
 */
class BaseApplication : MultiDexApplication() {
    override fun onCreate() {
        super.onCreate()

        INSTANCE = this

        HttpClient.getInstance().init()
        HttpClient2.getInstance().init()

        ToastUtil.init(this)
    }


    companion object {
        lateinit var INSTANCE: BaseApplication
    }
}