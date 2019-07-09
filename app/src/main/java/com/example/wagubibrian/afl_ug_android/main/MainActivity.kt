package com.example.wagubibrian.afl_ug_android.main

import android.annotation.SuppressLint
import android.os.Bundle
import android.support.design.widget.BottomNavigationView
import android.support.v4.app.DialogFragment
import android.support.v4.app.Fragment
import android.support.v7.app.AppCompatActivity
import com.example.wagubibrian.afl_ug_android.R
import com.example.wagubibrian.afl_ug_android.home.HomeFragment
import com.example.wagubibrian.afl_ug_android.match.MatchFragment

class MainActivity : AppCompatActivity() {

    @SuppressLint("SetTextI18n")
    private val onNavigationItemSelectedListener = BottomNavigationView.OnNavigationItemSelectedListener { item ->
        when (item.itemId) {
            R.id.navigation_home -> {
                switchNavView(HomeFragment.newInstance())
                return@OnNavigationItemSelectedListener true
            }
            R.id.navigation_dashboard -> {
                switchNavView(MatchFragment.newInstance())
                return@OnNavigationItemSelectedListener true
            }
            R.id.navigation_notifications -> {
                return@OnNavigationItemSelectedListener true
            }
        }
        false
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val navView: BottomNavigationView = findViewById(R.id.nav_view)

        addNavView(HomeFragment.newInstance())
        navView.setOnNavigationItemSelectedListener(onNavigationItemSelectedListener)
    }

    private fun addNavView(fragment: Fragment) {
        supportFragmentManager
            .beginTransaction()
            .add(R.id.main_frame_layout, fragment)
            .commit()
    }

    private fun switchNavView(fragment: Fragment) {
        supportFragmentManager
            .beginTransaction()
            .replace(R.id.main_frame_layout, fragment)
            .commit()
    }
}
