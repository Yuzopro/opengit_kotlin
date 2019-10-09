package com.yuzo.opengit.kotlin.ui.main

import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.google.android.material.tabs.TabLayoutMediator
import com.google.gson.Gson
import com.yuzo.lib.image.load
import com.yuzo.lib.tool.ToastUtil
import com.yuzo.lib.ui.BaseFragment
import com.yuzo.opengit.kotlin.R
import com.yuzo.opengit.kotlin.databinding.FragmentMainBinding
import com.yuzo.opengit.kotlin.http.service.bean.UserResponse
import com.yuzo.opengit.kotlin.sp.userSp
import com.yuzo.opengit.kotlin.ui.activity.MainActivity
import com.yuzo.opengit.kotlin.ui.fragment.HomeFragment
import kotlinx.android.synthetic.main.fragment_main.*

class MainFragment : BaseFragment<FragmentMainBinding>() {
    override val layoutId: Int = R.layout.fragment_main

    private lateinit var mainViewModel: MainViewModel

    private var user: UserResponse? = null

    private var tabs: List<String>? = null

    override fun initView() {
        super.initView()

        mainViewModel =
            ViewModelProviders.of(this).get(MainViewModel::class.java)

        load(mContext, iv_home_avatar, user?.avatarUrl)

        view_pager.adapter = object : FragmentStateAdapter(this) {
            override fun createFragment(position: Int): Fragment {
                return HomeFragment()
            }

            override fun getItemCount(): Int {
                return tabs!!.size
            }
        }
        view_pager.isUserInputEnabled = true

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

        user = Gson().fromJson(userSp, UserResponse::class.java)
    }

    fun launchSearch() {
        ToastUtil.showShort("launchSearch")
    }
}