package com.example.kyrs.presentation.edit_profile

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.net.Uri
import android.provider.MediaStore
import com.arellomobile.mvp.InjectViewState
import com.example.kyrs.data.entity.request.EditUserRequest
import com.example.kyrs.data.repository.ProfileRepository
import com.example.kyrs.data.sharedPref.AuthHolder
import com.example.kyrs.presentation.base.BasePresenter
import okhttp3.MediaType
import okhttp3.MultipartBody
import okhttp3.RequestBody
import java.io.File
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
    private val authHolder: AuthHolder,
    private val context: Context
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
                    val newImageUri = data!!.data

                    viewState.setImage(newImageUri)

                    sendAvatarRequest(newImageUri)
                }
            }
    }

    private fun sendAvatarRequest(newImageUri: Uri?) {

        var result: String? = null
        val proj = arrayOf(MediaStore.Images.Media.DATA)
        val cursor = context.contentResolver.query(newImageUri, proj, null, null, null)
        if (cursor != null) {
            if (cursor.moveToFirst()) {
                val column_index = cursor.getColumnIndexOrThrow(proj[0])
                result = cursor.getString(column_index)
            }
            cursor.close()
        }
        if (result == null) {
            result = "Not found"
        }

        val file = File(result)

        val requestFile = RequestBody.create(
            MediaType.parse(context.contentResolver.getType(newImageUri)!!),
            file
        )

        // MultipartBody.Part is used to send also the actual file name
        val body = MultipartBody.Part.createFormData("picture", file.name, requestFile)

        profileRepository.uploadAvatar(body)
            .subscribe({
                viewState.showMessage("ava loaded!")
            }, {
                viewState.showMessage(it.message.toString())
            }).connect()
    }

    fun onDoneClicked(name: String, surname: String, mail: String, phone: String) {
        val user = EditUserRequest(authHolder.userId, name, surname, mail, phone)

        profileRepository.editUser(user)
            .subscribe({
                viewState.showMessage(it.name.toString())
            }, {
                viewState.showMessage(it.message.toString())
            }).connect()
    }
}