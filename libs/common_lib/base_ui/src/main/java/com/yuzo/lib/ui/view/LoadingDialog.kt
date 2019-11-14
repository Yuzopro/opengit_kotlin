package com.yuzo.lib.ui.view

import android.app.Dialog
import android.content.Context
import com.yuzo.lib.ui.R

/**
 * Author: yuzo
 * Date: 2019-09-26
 */
class LoadingDialog constructor(context: Context) : Dialog(context) {
    init {
        setContentView(R.layout.layout_loading)

//        setCanceledOnTouchOutside(false)
        window?.setBackgroundDrawableResource(R.drawable.drawable_transparent)
        window?.setDimAmount(0.0f)
    }
}