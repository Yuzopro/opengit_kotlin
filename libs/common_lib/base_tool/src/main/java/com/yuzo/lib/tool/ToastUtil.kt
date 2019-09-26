package com.yuzo.lib.tool

import android.content.Context
import android.widget.Toast

/**
 * Author: yuzo
 * Date: 2019-09-26
 */
object ToastUtil {
    private var mContext: Context? = null

    fun init(context: Context) {
        mContext = context.applicationContext
    }

    fun showShort(text:String) {
        Toast.makeText(mContext, text, Toast.LENGTH_SHORT).show()
    }

    fun showShort(id : Int) {
        Toast.makeText(mContext, id, Toast.LENGTH_SHORT).show()
    }

    fun showLong(text:String) {
        Toast.makeText(mContext, text, Toast.LENGTH_LONG).show()
    }

    fun showLong(id : Int) {
        Toast.makeText(mContext, id, Toast.LENGTH_LONG).show()
    }
}