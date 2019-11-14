package com.yuzo.opengit.kotlin.ui.activity

import android.content.Intent
import android.os.Bundle
import android.view.Gravity
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.FragmentActivity
import com.yuzo.opengit.kotlin.R
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        initView()
    }

    private fun initView() {
//        vp_main_1.adapter = object :FragmentPagerAdapter(supportFragmentManager) {
//            override fun getItem(position: Int): Fragment {
//                if (position == 0) {
//                    return HomeFragment()
//                } else if (position == 1) {
//                    return RepoFragment()
//                } else if (position == 2) {
//                    return EventFragment()
//                } else {
//                    return IssueFragment()
//                }
//            }
//
//            override fun getCount(): Int = 4
//        }
//        vp_main_1.offscreenPageLimit = 3
//        vp_main_1.currentItem = 0
//
//        tv_test_1.setOnClickListener {
//            vp_main_1.currentItem = 0
//        }
//        tv_test_2.setOnClickListener {
//            vp_main_1.currentItem = 1
//        }
//        tv_test_3.setOnClickListener {
//            vp_main_1.currentItem = 2
//        }
//        tv_test_4.setOnClickListener {
//            vp_main_1.currentItem = 3
//        }
    }

    fun openDrawer() {
        drawer_layout?.openDrawer(Gravity.LEFT)
    }

    companion object {
        fun launch(activity: FragmentActivity) =
            activity.apply {
                startActivity(Intent(this, MainActivity::class.java))
                finish()
            }
    }
}
