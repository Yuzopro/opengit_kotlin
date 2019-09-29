package com.yuzo.opengit.kotlin.sp

/**
 * Author: yuzo
 * Date: 2019-09-29
 */
object SpDelegate {
    fun <T> preference(name: String, default: T) = Preference(name, default)
}