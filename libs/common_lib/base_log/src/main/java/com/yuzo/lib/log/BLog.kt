package com.yuzo.lib.log

import android.util.Log

/**
 * Author: yuzo
 * Date: 2019-09-29
 */
fun d(tag: String, msg: String) {
    Log.d(tag, msg)
}

fun e(tag: String, msg: String) {
    Log.e(tag, msg)
}

fun i(tag: String, msg: String) {
    Log.i(tag, msg)
}

fun v(tag: String, msg: String) {
    Log.v(tag, msg)
}

fun w(tag: String, msg: String) {
    Log.w(tag, msg)
}