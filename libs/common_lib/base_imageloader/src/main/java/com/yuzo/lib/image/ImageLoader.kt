package com.yuzo.lib.image

import android.content.Context
import android.widget.ImageView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions

/**
 * Author: yuzo
 * Date: 2019-09-30
 */
fun load(context: Context?, view: ImageView, url: String?) {
    context?.apply {
        Glide.with(this)
            .load(url)
            .placeholder(R.drawable.icon_default_head)
            .apply(RequestOptions().circleCrop())
            .into(view)
    }
}