package com.yuzo.lib.ui

import android.app.Dialog
import android.content.Context

/**
 * Author: yuzo
 * Date: 2019-09-26
 */
class LoadingDialog constructor(context: Context) : Dialog(context) {
    init {
        setContentView(R.layout.layout_loading)

        setCanceledOnTouchOutside(false)
        window?.setBackgroundDrawableResource(R.drawable.drawable_transparent)
    }
}