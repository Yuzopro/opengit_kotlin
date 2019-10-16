package com.yuzo.opengit.kotlin.ui.activity

import android.content.Intent
import android.os.Bundle
import android.view.Gravity
import android.view.Menu
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.FragmentActivity
import androidx.navigation.findNavController
import androidx.navigation.ui.setupWithNavController
import com.google.android.material.navigation.NavigationView
import com.google.gson.Gson
import com.yuzo.lib.image.load
import com.yuzo.opengit.kotlin.R
import com.yuzo.opengit.kotlin.http.service.bean.User
import com.yuzo.opengit.kotlin.sp.userSp
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val navView: NavigationView = findViewById(R.id.nav_view)
        val navController = findNavController(R.id.nav_host_fragment)
        navView.setupWithNavController(navController)

        val user = Gson().fromJson(userSp, User::class.java)

        val avatarView: ImageView = navView.getHeaderView(0).findViewById(R.id.iv_nav_header_avatar)
        load(avatarView.context, avatarView, user.avatarUrl)

        val nameView: TextView = navView.getHeaderView(0).findViewById(R.id.tv_nav_header_name)
        nameView.text = user.login

        val emailView: TextView = navView.getHeaderView(0).findViewById(R.id.tv_nav_header_email)
        emailView.text = user.email

    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.main, menu)
        return true
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
