package com.yuzo.opengit.kotlin.ui.fragment.about

import android.os.Bundle
import com.yuzo.lib.ui.fragment.BaseFragment
import com.yuzo.lib.ui.fragment.BaseWebFragment
import com.yuzo.opengit.kotlin.R
import com.yuzo.opengit.kotlin.databinding.FragmentThanksBinding
import kotlinx.android.synthetic.main.fragment_thanks.*

/**
 * Author: yuzo
 * Date: 2019-11-28
 */
class ThanksFragment : BaseFragment<FragmentThanksBinding>() {
    override val layoutId: Int = R.layout.fragment_thanks

    override fun initView() {
        super.initView()

        tv_header_center?.text = getString(R.string.about_text_thanks)

        tv_header_left?.setOnClickListener {
            pop()
        }

        tv_thanks_github_api?.setOnClickListener {
            openWeb("https://developer.github.com/v3/")
        }
    }

    private fun openWeb(url : String) {
        val bundle = Bundle()
        bundle.putString(BaseWebFragment.KEY_URL, url)
        nav().navigate(R.id.action_thanks_to_web, bundle)
    }
}