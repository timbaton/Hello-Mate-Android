package com.example.kyrs.presentation.edit_profile

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.net.Uri
import com.arellomobile.mvp.InjectViewState
import com.example.kyrs.R
import com.example.kyrs.data.entity.request.EditUserRequest
import com.example.kyrs.data.repository.GalleryRepository
import com.example.kyrs.data.repository.ProfileRepository
import com.example.kyrs.data.sharedPref.AuthHolder
import com.example.kyrs.presentation.base.BasePresenter
import com.example.kyrs.utils.ResourceManager
import javax.inject.Inject


/**
 * Project HelloMate
 * Package com.example.kyrs.presentation.edit_profile
 *
 *
 *
 * Created by Timur Badretdinov (aka timurbadretdinov) 2019-08-14
 */
@InjectViewState
class EditProfilePresenter @Inject constructor(
    private val profileRepository: ProfileRepository,
    private val galleryRepository: GalleryRepository,
    private val resourceManager: ResourceManager,
    private val authHolder: AuthHolder
) : BasePresenter<EditProfileView>() {

    private val GALLERY_REQUEST = 33

    override fun onFirstViewAttach() {
        super.onFirstViewAttach()

        val user = profileRepository.getUserLocal()
        viewState.fillUserData(user)
    }

    fun onBackPressed() {
        viewState.onBackPressed()
    }

    fun onAddAvatarClicked() {
        viewState.showGallery(GALLERY_REQUEST)
    }

    fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        if (resultCode == Activity.RESULT_OK)
            when (requestCode) {
                GALLERY_REQUEST -> {
                    if (data != null) {
                        val newImageUri = data.data!!
                        viewState.setImage(newImageUri)

                        sendAvatarRequest(newImageUri)
                    } else {
                        viewState.showMessage(resourceManager.getString(R.string.error))
                    }
                }
            }
    }

    private fun sendAvatarRequest(newImageUri: Uri) {

        galleryRepository.loadImage(newImageUri)
            .flatMap {
                profileRepository.uploadAvatar(it)

            }
            .subscribe({
                viewState.fillUserData(it)
                viewState.onBackPressed()
            }, {
                viewState.showMessage(it.message.toString())
            }).connect()


    }

    fun onDoneClicked(name: String, surname: String, mail: String, phone: String) {
        val user = EditUserRequest(authHolder.userId, name, surname, mail, phone)

        profileRepository.editUser(user)
            .subscribe({
                viewState.fillUserData(it)
                viewState.finishActivityOk()
            }, {
                viewState.showMessage(it.message.toString())
            }).connect()
    }
}