package com.yuzo.opengit.kotlin

import androidx.multidex.MultiDexApplication
import com.yuzo.lib.tool.ToastUtil

/**
 * Author: yuzo
 * Date: 2019-09-25
 */
class BaseApplication : MultiDexApplication() {
    override fun onCreate() {
        super.onCreate()

        ToastUtil.init(this)
    }
}