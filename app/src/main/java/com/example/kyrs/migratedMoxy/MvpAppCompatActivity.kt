package com.example.kyrs.migratedMoxy

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.arellomobile.mvp.MvpDelegate

/**
 * Project HelloMate
 * Package com.example.kyrs
 *
 *
 *
 * Created by Timur Badretdinov (aka timurbadretdinov) 2019-08-13
 * Copyright © 2018 SuperEgo. All rights reserved.
 */
open class MvpAppCompatActivity : AppCompatActivity() {
    private var mMvpDelegate: MvpDelegate<out MvpAppCompatActivity>? = null

    /**
     * @return The [MvpDelegate] being used by this Activity.
     */
    val mvpDelegate: MvpDelegate<*>
        get() {
            if (mMvpDelegate == null) {
                mMvpDelegate = MvpDelegate(this)
            }
            return mMvpDelegate!!
        }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        mvpDelegate.onCreate(savedInstanceState)
    }

    override fun onStart() {
        super.onStart()

        mvpDelegate.onAttach()
    }

    override fun onResume() {
        super.onResume()

        mvpDelegate.onAttach()
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)

        mvpDelegate.onSaveInstanceState(outState)
        mvpDelegate.onDetach()
    }

    override fun onStop() {
        super.onStop()

        mvpDelegate.onDetach()
    }

    override fun onDestroy() {
        super.onDestroy()

        mvpDelegate.onDestroyView()

        if (isFinishing()) {
            mvpDelegate.onDestroy()
        }
    }
}