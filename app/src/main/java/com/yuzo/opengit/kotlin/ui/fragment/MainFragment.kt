package com.yuzo.opengit.kotlin.ui.fragment

import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.google.android.material.tabs.TabLayoutMediator
import com.google.gson.Gson
import com.yuzo.lib.image.load
import com.yuzo.lib.tool.ToastUtil
import com.yuzo.lib.ui.fragment.BaseFragment
import com.yuzo.opengit.kotlin.R
import com.yuzo.opengit.kotlin.databinding.FragmentMainBinding
import com.yuzo.opengit.kotlin.http.service.bean.User
import com.yuzo.opengit.kotlin.sp.userSp
import com.yuzo.opengit.kotlin.ui.activity.MainActivity
import kotlinx.android.synthetic.main.fragment_main.*

class MainFragment : BaseFragment<FragmentMainBinding>() {
    override val layoutId: Int = R.layout.fragment_main

    private var user: User? = null

    private var tabs: List<String>? = null

    override fun initView() {
        super.initView()

        load(mContext, iv_home_avatar, user?.avatarUrl)

        view_pager.adapter = object : FragmentStateAdapter(this) {
            override fun createFragment(position: Int): Fragment {
                if (position == 0) {
                    return HomeFragment()
                } else if (position == 1) {
                    return RepoFragment()
                } else if (position == 2) {
                    return EventFragment()
                } else {
                    return IssueFragment()
                }
            }

            override fun getItemCount(): Int {
                return tabs!!.size
            }
        }
        view_pager.isUserInputEnabled = true
        view_pager.offscreenPageLimit = tabs!!.size - 1

        TabLayoutMediator(tab_layout, view_pager) { tab, position ->
            tab.text = tabs!![position]
        }.attach()
    }

    override fun initData(binding: FragmentMainBinding) {
        super.initData(binding)

        tabs = listOf(
            getString(R.string.home_tab_0),
            getString(R.string.home_tab_1),
            getString(R.string.home_tab_2),
            getString(R.string.home_tab_3)
        )

        binding.fragment = this
        binding.activity = activity as MainActivity

        user = Gson().fromJson(userSp, User::class.java)
    }

    fun launchSearch() {
        ToastUtil.showShort("launchSearch")
    }
}