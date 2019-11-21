package com.yuzo.lib.ui.activity

import android.content.Context
import android.content.Intent
import android.graphics.Color
import android.text.TextUtils
import android.view.KeyEvent
import android.view.ViewGroup
import android.webkit.WebView
import com.yuzo.lib.ui.R
import com.yuzo.lib.ui.databinding.ActivityBaseLayoutBinding
import com.yuzo.lib.ui.view.webview.BaseAgentWebActivity
import kotlinx.android.synthetic.main.activity_base_layout.*

/**
 * Author: yuzo
 * Date: 2019-11-21
 */
class BaseWebActivity : BaseAgentWebActivity<ActivityBaseLayoutBinding>() {
    private var url: String = ""

    override val layoutId: Int = R.layout.activity_base_layout

    override fun initData(binding: ActivityBaseLayoutBinding) {
        super.initData(binding)

        url = intent.getStringExtra(KEY_URL)
    }

    override fun getAgentWebParent(): ViewGroup = fl_container as ViewGroup

    override fun onKeyDown(keyCode: Int, event: KeyEvent): Boolean {
        return if (agentWeb != null && agentWeb!!.handleKeyEvent(keyCode, event)) {
            true
        } else super.onKeyDown(keyCode, event)

    }

    override var indicatorColor: Int = Color.parseColor("#73D6D0")

    override fun setTitle(view: WebView, title: String) {
        var title = title
        super.setTitle(view, title)
        if (!TextUtils.isEmpty(title)) {
            if (title.length > 10) {
                title = title.substring(0, 10) + "..."
            }
        }
        setHeaderCenter(title)
    }

    override val indicatorHeight: Int = 3

    override fun getUrl(): String = url

    companion object {
        private const val KEY_URL = "key_url"

        fun gotoActivity(context: Context, url : String) {
            val intent = Intent(context, BaseWebActivity::class.java)
            intent.putExtra(KEY_URL, url)
            context.startActivity(intent)
        }
    }
}
