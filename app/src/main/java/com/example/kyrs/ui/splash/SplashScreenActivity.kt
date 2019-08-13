package com.example.kyrs.ui.splash

import android.content.Intent
import android.os.Bundle
import android.util.Log
import com.example.kyrs.presentation.splash.SplashScreenView
import com.example.kyrs.ui.base.BaseActivity
import android.view.View
import com.arellomobile.mvp.presenter.InjectPresenter
import com.arellomobile.mvp.presenter.ProvidePresenter
import com.example.kyrs.R
import com.example.kyrs.di.Scopes
import com.example.kyrs.presentation.splash.SplashScreenPresenter
import com.example.kyrs.ui.login.LoginScreenActivity
import toothpick.Toothpick


/**
 * Project Kyrs
 * Package com.example.kyrs.ui.splash_background
 *
 *
 *
 * Created by Timur Badretdinov (aka timurbadretdinov) 2019-06-03
 * Copyright © 2018 SuperEgo. All rights reserved.
 */
class SplashScreenActivity : BaseActivity(), SplashScreenView {

    override var res: Int? = null

    @InjectPresenter
    lateinit var presenter: SplashScreenPresenter

    @ProvidePresenter
    fun providePresenter(): SplashScreenPresenter {
        return Toothpick.openScope(Scopes.Server).getInstance(SplashScreenPresenter::class.java)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun openLoginScreen() {
        Log.i("MyTag", "starting activity")
        startActivity(LoginScreenActivity.getIntent(this))
        finish()
    }
}