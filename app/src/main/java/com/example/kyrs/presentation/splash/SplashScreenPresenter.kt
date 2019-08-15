package com.example.kyrs.presentation.splash

import com.arellomobile.mvp.InjectViewState
import com.example.kyrs.data.sharedPref.AuthHolder
import com.example.kyrs.presentation.base.BasePresenter
import javax.inject.Inject

/**
 * Project Kyrs
 * Package com.example.kyrs.presentation.splash_background
 *
 *
 *
 * Created by Timur Badretdinov (aka timurbadretdinov) 2019-06-03
 */
@InjectViewState
class SplashScreenPresenter @Inject constructor(
    private var authHolder: AuthHolder
) : BasePresenter<SplashScreenView>() {

    override fun onFirstViewAttach() {
        super.onFirstViewAttach()

        if (authHolder.userId != 0) {
            viewState.openMainScreen()
        } else {
            viewState.openLoginScreen()
        }
    }
}