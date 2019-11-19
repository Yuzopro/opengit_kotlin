package com.yuzo.opengit.kotlin.ui.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentPagerAdapter
import com.google.gson.Gson
import com.yuzo.lib.image.load
import com.yuzo.lib.log.v
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

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        v("MainFragment", "onCreate")
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        v("MainFragment", "onCreateView")

        return super.onCreateView(inflater, container, savedInstanceState)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        v("MainFragment", "onViewCreated")
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        v("MainFragment", "onActivityCreated")
    }

    override fun onDestroyView() {
        super.onDestroyView()

        v("MainFragment", "onDestroyView")
    }

    override fun initView() {
        super.initView()

        load(mContext, iv_home_avatar, user?.avatarUrl)

        view_pager.adapter = object : FragmentPagerAdapter(childFragmentManager) {
            override fun getItem(position: Int): Fragment {
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

            override fun getCount(): Int {
                return tabs!!.size
            }

            override fun getPageTitle(position: Int): CharSequence? {
                return tabs?.get(position)
            }
        }
        view_pager.offscreenPageLimit = tabs!!.size
        view_pager.currentItem = 0

        tab_layout?.setupWithViewPager(view_pager)
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
//        SearchActivity.gotoActivity(context!!)
        nav().navigate(R.id.action_drawer_to_gallery1)
    }
}