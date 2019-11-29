package com.yuzo.opengit.kotlin.ui.fragment.about

import android.os.Bundle
import androidx.navigation.Navigation
import com.yuzo.lib.ui.fragment.BaseFragment
import com.yuzo.lib.ui.fragment.BaseWebFragment
import com.yuzo.opengit.kotlin.R
import com.yuzo.opengit.kotlin.databinding.FragmentAuthorBinding
import kotlinx.android.synthetic.main.fragment_author.*

/**
 * Author: yuzo
 * Date: 2019-11-28
 */
class AuthorFragment : BaseFragment<FragmentAuthorBinding>() {
    override val layoutId: Int = R.layout.fragment_author

    override fun initView() {
        super.initView()

        tv_header_center?.text = getString(R.string.about_text_author)

        tv_header_left?.setOnClickListener {
            pop()
        }

        tv_author_github?.setOnClickListener {
            openWeb("https://github.com/yuzopro")
        }
        tv_author_blog?.setOnClickListener {
            openWeb("https://yuzopro.github.io/")
        }
        tv_author_juejin?.setOnClickListener {
            openWeb("https://juejin.im/user/56ea9d7ca341310054a57b7c")
        }
        tv_author_jianshu?.setOnClickListener {
            openWeb("https://www.jianshu.com/u/ef3cb65219d4")
        }
        tv_author_csdn?.setOnClickListener {
            openWeb("https://blog.csdn.net/Yuzopro")
        }
    }

    private fun openWeb(url : String) {
        val bundle = Bundle()
        bundle.putString(BaseWebFragment.KEY_URL, url)
        nav().navigate(R.id.action_author_to_web, bundle)
    }
}