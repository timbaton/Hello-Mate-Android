package com.example.kyrs.ui.main

import android.content.Context
import android.content.Intent
import android.os.Bundle
import com.google.android.material.bottomnavigation.BottomNavigationView
import androidx.fragment.app.Fragment
import com.example.kyrs.R
import com.example.kyrs.presentation.main.MainScreenView
import com.example.kyrs.ui.base.BaseActivity
import com.example.kyrs.ui.plans.PlansFragment
import com.example.kyrs.ui.plans.ProfileFragment
import com.example.kyrs.ui.plans.SearchFragment

/**
 * Project HelloMate
 * Package com.example.kyrs.ui.main
 *
 *
 *
 * Created by Timur Badretdinov (aka timurbadretdinov) 2019-07-09
 * Copyright © 2018 SuperEgo. All rights reserved.
 */
class MainContainerActivity : BaseActivity(), MainScreenView {

    override var res: Int? = R.layout.activity_main

    companion object {

        fun getIntent(context: Context): Intent {
            val intent = Intent(context, MainContainerActivity::class.java)
            return intent
        }
    }

    private val fragment1: Fragment = SearchFragment()
    private val fragment2: Fragment = PlansFragment()
    private val fragment3: Fragment = ProfileFragment()
    private var active = fragment1

    private val fm = supportFragmentManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val navigation = findViewById<BottomNavigationView>(R.id.navigation)
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener)

        fm.beginTransaction().add(R.id.main_container, fragment3, "3").hide(fragment3).commit()
        fm.beginTransaction().add(R.id.main_container, fragment2, "2").hide(fragment2).commit()
        fm.beginTransaction().add(R.id.main_container, fragment1, "1").commit()

    }

    private val mOnNavigationItemSelectedListener =
        BottomNavigationView.OnNavigationItemSelectedListener { item ->
            when (item.itemId) {
                R.id.navigation_search -> {
                    fm.beginTransaction().hide(active).show(fragment1).commit()
                    active = fragment1
                    return@OnNavigationItemSelectedListener true
                }

                R.id.navigation_plans -> {
                    fm.beginTransaction().hide(active).show(fragment2).commit()
                    active = fragment2
                    return@OnNavigationItemSelectedListener true
                }

                R.id.navigation_profile -> {
                    fm.beginTransaction().hide(active).show(fragment3).commit()
                    active = fragment3
                    return@OnNavigationItemSelectedListener true
                }
            }
            false
        }
}