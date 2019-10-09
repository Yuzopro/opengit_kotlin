package com.yuzo.opengit.kotlin.ui.paging

import androidx.paging.DataSource

/**
 * Author: yuzo
 * Date: 2019-10-09
 */
class HomeDataSourceFactory:DataSource.Factory<Int, String>() {
    override fun create(): DataSource<Int, String> = HomeDataSource()
}