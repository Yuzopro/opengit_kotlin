package com.yuzo.lib.ui.repository

/**
 * Author: yuzo
 * Date: 2019-11-14
 */
interface BaseRepository<T> {
    fun post(state : Int, pageSize : Int) : Listing<T>
}