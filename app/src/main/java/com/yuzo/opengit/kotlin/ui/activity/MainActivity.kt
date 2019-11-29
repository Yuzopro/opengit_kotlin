package com.yuzo.opengit.kotlin.ui.activity

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.GravityCompat
import androidx.drawerlayout.widget.DrawerLayout
import androidx.fragment.app.FragmentActivity
import androidx.navigation.Navigation
import com.yuzo.lib.ui.fragment.BaseWebFragment
import com.yuzo.opengit.kotlin.R
import com.yuzo.opengit.kotlin.ui.DrawerCoordinateHelper
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity(), DrawerCoordinateHelper.OnDrawerLockListener {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        DrawerCoordinateHelper.getInstance().listener = this
    }

    fun openDrawer() {
        drawer_layout?.openDrawer(GravityCompat.START)
    }

    override fun onBackPressed() {
        val nav = Navigation.findNavController(this, R.id.nav_host_fragment)
        if (nav.currentDestination != null && nav.currentDestination!!.id != R.id.nav_home) {
            nav.navigateUp()
        } else if (drawer_layout != null && drawer_layout.isDrawerOpen(GravityCompat.START)) {
            drawer_layout.closeDrawer(GravityCompat.START)
        } else {
            super.onBackPressed()
        }
    }

    override fun onDestroy() {
        super.onDestroy()

        DrawerCoordinateHelper.getInstance().listener = null
    }

    override fun onDrawerLock(isUnLock: Boolean) {
        drawer_layout.setDrawerLockMode(
            if (isUnLock)
                DrawerLayout.LOCK_MODE_UNLOCKED
            else
                DrawerLayout.LOCK_MODE_LOCKED_CLOSED
        )
    }

    fun onDrawerItemClick(position: Int) {
        val nav = Navigation.findNavController(this, R.id.nav_host_fragment)
        when (position) {
            0 -> {
                openWeb("https://github.com/trending")
            }
            1 -> {
                nav.navigate(R.id.action_drawer_to_about)
            }
            2 -> {
//                nav.navigate(R.id.action_drawer_to_setting)
            }
            3 -> {
//                nav.navigate(R.id.action_drawer_to_about)
            }
            4 -> {
            }
            5 -> {
            }
        }
    }

    fun openWeb(url : String) {
        val nav = Navigation.findNavController(this, R.id.nav_host_fragment)

        val bundle = Bundle()
        bundle.putString(BaseWebFragment.KEY_URL, url)
        nav.navigate(R.id.action_to_drawer_web, bundle)
    }

    companion object {
        fun launch(activity: FragmentActivity) =
            activity.apply {
                startActivity(Intent(this, MainActivity::class.java))
                finish()
            }
    }
}
