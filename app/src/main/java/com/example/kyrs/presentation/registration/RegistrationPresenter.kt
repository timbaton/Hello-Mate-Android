package com.example.kyrs.presentation.registration

import com.arellomobile.mvp.InjectViewState
import com.example.kyrs.data.repository.AuthRepository
import com.example.kyrs.presentation.base.BasePresenter
import javax.inject.Inject

/**
 * Project HelloMate
 * Package com.example.kyrs.presentation.registration
 *
 *
 *
 * Created by Timur Badretdinov (aka timurbadretdinov) 2019-07-09
 * Copyright © 2018 SuperEgo. All rights reserved.
 */
@InjectViewState
class RegistrationPresenter @Inject constructor(
    private val authRepository: AuthRepository
) : BasePresenter<RegistrationView>() {

    fun onBackCliecked() {
        viewState.back()
    }
}