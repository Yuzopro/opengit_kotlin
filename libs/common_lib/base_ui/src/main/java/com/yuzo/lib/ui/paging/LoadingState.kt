package com.yuzo.lib.ui.paging

/**
 * Author: yuzo
 * Date: 2019-10-10
 */
enum class LoadingState {
    Normal, //默认状态
    Loading, //加载中
    Failed, //出错重试
    NotMore,//没有更多数据
    Empty//空视图
}