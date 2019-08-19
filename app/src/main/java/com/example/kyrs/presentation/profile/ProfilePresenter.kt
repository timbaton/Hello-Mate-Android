package com.example.kyrs.presentation.profile

import android.app.Activity
import com.arellomobile.mvp.InjectViewState
import com.example.kyrs.data.repository.ProfileRepository
import com.example.kyrs.di.ServerPath
import com.example.kyrs.presentation.base.BasePresenter
import com.example.kyrs.ui.plans.PlansFragment
import com.example.kyrs.ui.plans.ProfileActivity
import com.example.kyrs.ui.profile.ProfileFragment
import javax.inject.Inject

/**
 * Project HelloMate
 * Package com.example.kyrs.presentation.profile
 *
 *
 *
 * Created by Timur Badretdinov (aka timurbadretdinov) 2019-08-06
 */
@InjectViewState
class ProfilePresenter @Inject constructor(
    private var profileRepository: ProfileRepository,
    @ServerPath private var serverPath: String
) : BasePresenter<ProfileView>() {

    var userId = 0

    override fun onFirstViewAttach() {
        super.onFirstViewAttach()

        loadProfile(userId)
    }

    private fun loadProfile(userId: Int) {
        profileRepository.loadProfile(userId)
            .subscribe({
                viewState.fillProfileData(it, serverPath)

                if (it.mail == null || it.phone == null) {
                    viewState.hideUserContacts()
                } else {
                    viewState.showUserContacts()
                }

                profileRepository.saveUserLocal(it)
            }, {
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

    fun onEditClicked() {
        viewState.openEditProfileActivity()
    }

    fun onActivityResult(requestCode: Int, resultCode: Int) {
        if (ProfileFragment.REQUEST_EDIT_PROFILE == requestCode && resultCode == Activity.RESULT_OK) {
           loadProfile(userId)
        }
    }
}