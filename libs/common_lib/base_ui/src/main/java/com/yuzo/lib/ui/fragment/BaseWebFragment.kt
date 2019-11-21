package com.yuzo.lib.ui.fragment

import android.graphics.Color
import android.text.TextUtils
import android.view.View
import android.view.ViewGroup
import android.webkit.WebView
import com.yuzo.lib.ui.R
import com.yuzo.lib.ui.databinding.FragmentWebBinding
import com.yuzo.lib.ui.view.webview.BaseAgentWebFragment
import kotlinx.android.synthetic.main.fragment_web.*
import kotlinx.android.synthetic.main.layout_toolbar_web.*

/**
 * Author: yuzo
 * Date: 2019-11-21
 */
class BaseWebFragment : BaseAgentWebFragment<FragmentWebBinding>(), View.OnClickListener {
    override val layoutId: Int = R.layout.fragment_web

    override fun getAgentWebParent(): ViewGroup {
        return linearLayout
    }

    override fun initView() {
        super.initView()
        iv_web_back?.setOnClickListener(this);
        iv_web_close?.setOnClickListener(this);
    }

    override var indicatorColor: Int = Color.parseColor("#73D6D0")

    override val indicatorHeight: Int = 3

    override fun setTitle(view: WebView, title: String) {
        var title = title
        super.setTitle(view, title)
        if (!TextUtils.isEmpty(title)) {
            if (title.length > 10) {
                title = title.substring(0, 10) + "..."
            }
        }
        tv_web_header_center?.text = title
    }

    override fun getUrl(): String? {
        return arguments!!.getString(KEY_URL)
    }

    override fun onClick(v: View?) {
        val id = v?.id
        if (id == R.id.iv_web_back) {
            if (mAgentWeb != null && !mAgentWeb!!.back()) {
                nav().popBackStack()
            }
        } else if (id == R.id.iv_web_close) {
            nav().popBackStack()
        }
    }

    companion object {
        const val KEY_URL = "key_url"
    }
}
