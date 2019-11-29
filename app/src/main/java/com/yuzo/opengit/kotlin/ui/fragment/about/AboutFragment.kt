package com.yuzo.opengit.kotlin.ui.fragment.about

import android.os.Bundle
import com.yuzo.lib.ui.fragment.BaseFragment
import com.yuzo.lib.ui.fragment.BaseWebFragment
import com.yuzo.opengit.kotlin.BuildConfig
import com.yuzo.opengit.kotlin.R
import com.yuzo.opengit.kotlin.databinding.FragmentAboutBinding
import com.yuzo.opengit.kotlin.ui.DrawerCoordinateHelper
import kotlinx.android.synthetic.main.fragment_about.*

class AboutFragment : BaseFragment<FragmentAboutBinding>() {
    override val layoutId: Int = R.layout.fragment_about


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        lifecycle.addObserver(DrawerCoordinateHelper.getInstance())
    }

    override fun initView() {
        super.initView()

        tv_about_version?.text = getString(R.string.about_text_version, BuildConfig.VERSION_NAME)
        tv_header_center?.text = getString(R.string.drawer_item_about)

        tv_header_left?.setOnClickListener {
            pop()
        }
        tv_about_github?.setOnClickListener {
            val bundle = Bundle()
            bundle.putString(BaseWebFragment.KEY_URL, "https://github.com/Yuzopro/opengit_kotlin")
            nav().navigate(R.id.action_about_to_web, bundle)
        }
        tv_about_author?.setOnClickListener {
            nav().navigate(R.id.action_about_to_author)
        }
        tv_about_thanks?.setOnClickListener {
            nav().navigate(R.id.action_about_to_thanks)
        }
    }
}