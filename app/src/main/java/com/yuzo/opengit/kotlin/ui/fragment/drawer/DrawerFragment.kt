package com.yuzo.opengit.kotlin.ui.fragment.drawer

import com.google.gson.Gson
import com.yuzo.lib.ui.fragment.BaseFragment
import com.yuzo.opengit.kotlin.R
import com.yuzo.opengit.kotlin.databinding.FragmentDrawerBinding
import com.yuzo.opengit.kotlin.http.service.bean.User
import com.yuzo.opengit.kotlin.sp.userSp
import com.yuzo.opengit.kotlin.ui.activity.MainActivity
import com.yuzo.opengit.kotlin.ui.adapter.DrawerAdapter
import com.yuzo.opengit.kotlin.ui.bean.DrawerBean
import kotlinx.android.synthetic.main.fragment_drawer.*

/**
 * Author: yuzo
 * Date: 2019-11-01
 */
class DrawerFragment : BaseFragment<FragmentDrawerBinding>(), DrawerAdapter.OnItemClickListener {
    override val layoutId: Int = R.layout.fragment_drawer

    private var adapter: DrawerAdapter? = null

    override fun initView() {
        super.initView()

        adapter = DrawerAdapter()
        adapter!!.listener = this
        rv_drawer_list.adapter = adapter

        val list: MutableList<DrawerBean> = ArrayList()
        list.add(DrawerBean(R.drawable.icon_trend, R.string.drawer_item_trend))
        list.add(DrawerBean(R.drawable.icon_track, R.string.drawer_item_track))
        list.add(DrawerBean(R.drawable.icon_setting, R.string.drawer_item_setting))
        list.add(DrawerBean(R.drawable.icon_about, R.string.drawer_item_about))
        list.add(DrawerBean(R.drawable.icon_share, R.string.drawer_item_share))
        list.add(DrawerBean(R.drawable.icon_logout, R.string.drawer_item_logout))

        adapter!!.addDatas(list)
    }

    override fun initData(binding: FragmentDrawerBinding) {
        super.initData(binding)

        binding.user = Gson().fromJson(userSp, User::class.java)
    }

    override fun OnItemClick(position: Int) {
        val hostActivity = activity as MainActivity
        hostActivity.onDrawerItemClick(position)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        adapter?.listener = null
    }
}