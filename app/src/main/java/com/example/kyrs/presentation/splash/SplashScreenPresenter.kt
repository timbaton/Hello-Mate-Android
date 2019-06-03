package com.example.kyrs.presentation.splash

import com.arellomobile.mvp.InjectViewState
import com.example.kyrs.presentation.base.BasePresenter
import javax.inject.Inject

/**
 * Project Kyrs
 * Package com.example.kyrs.presentation.splash
 *
 *
 *
 * Created by Timur Badretdinov (aka timurbadretdinov) 2019-06-03
 * Copyright © 2018 SuperEgo. All rights reserved.
 */
@InjectViewState
class SplashScreenPresenter @Inject constructor() : BasePresenter<SplashScreenView>() {

    override fun onFirstViewAttach() {
        super.onFirstViewAttach()

        viewState.openLoginScreen()
    }
}