package com.example.kyrs.presentation.profile

import com.arellomobile.mvp.InjectViewState
import com.example.kyrs.data.repository.ProfileRepository
import com.example.kyrs.di.ServerPath
import com.example.kyrs.presentation.base.BasePresenter
import javax.inject.Inject

/**
 * Project HelloMate
 * Package com.example.kyrs.presentation.profile
 *
 *
 *
 * Created by Timur Badretdinov (aka timurbadretdinov) 2019-08-06
 * Copyright © 2018 SuperEgo. All rights reserved.
 */
@InjectViewState
class ProfilePresenter @Inject constructor(
    private var profileRepository: ProfileRepository,
    @ServerPath private var serverPath: String
): BasePresenter<ProfileView>() {

    var userId = 0

    override fun onFirstViewAttach() {
        super.onFirstViewAttach()

        loadProfile(userId)
    }

    private fun loadProfile(userId: Int) {
        profileRepository.loadProfile(userId)
            .subscribe({
                viewState.fillProfileData(it, serverPath)
            },{
                viewState.showMessage(it.message.toString())
            }).connect()
    }

    fun onExitClicked() {
        profileRepository.exit()

        viewState.openLoginActivity()
    }

    fun onBackPressed() {
        viewState.onBackPressed()
    }
}