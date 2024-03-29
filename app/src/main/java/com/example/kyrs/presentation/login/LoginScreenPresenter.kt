package com.example.kyrs.presentation.login

import com.arellomobile.mvp.InjectViewState
import com.example.kyrs.R
import com.example.kyrs.data.repository.AuthRepository
import com.example.kyrs.presentation.base.BasePresenter
import com.example.kyrs.utils.ResourceManager
import javax.inject.Inject


/**
 * Project Kyrs
 * Package com.example.kyrs.presenters
 *
 *
 *
 * Created by Timur Badretdinov (aka timurbadretdinov) 2019-05-31
 */
@InjectViewState
class LoginScreenPresenter @Inject constructor(
    private var authRepository: AuthRepository
) : BasePresenter<LoginScreenView>() {

    fun onLoginClicked(login: String, password: String) {
        authRepository.login(login, password)
            .subscribe({
                authRepository.saveAuthData(it.userId, it.token)
                viewState.openMainScreen()
            }, {
                viewState.showMessage(it.message.toString())
            }).connect()
    }

    fun onRegisterClicked() {
        viewState.openRegistrationActivity()
    }
}