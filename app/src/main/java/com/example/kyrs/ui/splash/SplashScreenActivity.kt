package com.example.kyrs.ui.splash

import com.arellomobile.mvp.presenter.InjectPresenter
import com.arellomobile.mvp.presenter.ProvidePresenter
import com.example.kyrs.di.Scopes
import com.example.kyrs.presentation.splash.SplashScreenPresenter
import com.example.kyrs.presentation.splash.SplashScreenView
import com.example.kyrs.ui.base.BaseActivity
import com.example.kyrs.ui.login.LoginScreenActivity
import com.example.kyrs.ui.main.MainContainerActivity
import toothpick.Toothpick


/**
 * Project Kyrs
 * Package com.example.kyrs.ui.splash_background
 *
 *
 *
 * Created by Timur Badretdinov (aka timurbadretdinov) 2019-06-03
 */
class SplashScreenActivity : BaseActivity(), SplashScreenView {

    override var res: Int? = null

    @InjectPresenter
    lateinit var presenter: SplashScreenPresenter

    @ProvidePresenter
    fun providePresenter(): SplashScreenPresenter {
        return Toothpick.openScope(Scopes.Server).getInstance(SplashScreenPresenter::class.java)
    }

    override fun openLoginScreen() {
        startActivity(LoginScreenActivity.getIntent(this))
        finish()
    }

    override fun openMainScreen() {
        startActivity(MainContainerActivity.getIntent(this))
        finish()
    }
}