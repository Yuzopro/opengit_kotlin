package com.yuzo.opengit.kotlin.ui.fragment.drawer

import android.os.Bundle
import com.yuzo.lib.ui.fragment.BaseFragment
import com.yuzo.opengit.kotlin.BuildConfig
import com.yuzo.opengit.kotlin.R
import com.yuzo.opengit.kotlin.databinding.FragmentShareBinding
import com.yuzo.opengit.kotlin.ui.DrawerCoordinateHelper
import kotlinx.android.synthetic.main.fragment_about.tv_header_center
import kotlinx.android.synthetic.main.fragment_about.tv_header_left
import kotlinx.android.synthetic.main.fragment_share.*

class ShareFragment : BaseFragment<FragmentShareBinding>() {
    override val layoutId: Int = R.layout.fragment_share

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        lifecycle.addObserver(DrawerCoordinateHelper.getInstance())
    }

    override fun initView() {
        super.initView()

        tv_share_version?.text = BuildConfig.VERSION_NAME
        tv_header_center?.text = getString(R.string.drawer_item_share)

        tv_header_left?.setOnClickListener {
            pop()
        }
    }

}