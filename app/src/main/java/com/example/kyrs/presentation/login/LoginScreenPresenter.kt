package com.example.kyrs.presentation.login

import com.arellomobile.mvp.InjectViewState
import com.example.kyrs.data.repository.AuthRepository
import com.example.kyrs.presentation.base.BasePresenter
import javax.inject.Inject


/**
 * Project Kyrs
 * Package com.example.kyrs.presenters
 *
 *
 *
 * Created by Timur Badretdinov (aka timurbadretdinov) 2019-05-31
 * Copyright © 2018 SuperEgo. All rights reserved.
 */
@InjectViewState
class LoginScreenPresenter @Inject constructor(
    private var authRepository: AuthRepository
) : BasePresenter<LoginScreenView>() {

    fun onLoginClicked(login: String, password: String) {
        authRepository.login(login, password)
            .subscribe({
                authRepository.saveAuthData(it.userId, it.token)
            }, {
                viewState.showMessage("something is wrong")
            }).connect()
    }

    fun onRegistrateCliecked() {
        viewState.openRegistrationActivity()
    }
}