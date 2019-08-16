package com.example.kyrs.presentation.edit_profile

import android.net.Uri
import com.example.kyrs.data.entity.response.ProfileResponse
import com.example.kyrs.presentation.base.BaseView

/**
 * Project HelloMate
 * Package com.example.kyrs.presentation.edit_profile
 *
 *
 *
 * Created by Timur Badretdinov (aka timurbadretdinov) 2019-08-14
 */
interface EditProfileView : BaseView {

    fun fillUserData(user: ProfileResponse)

    fun onBackPressed()

    fun showGallery(galleryRequest: Int)
    fun setImage(selectedImage: Uri?)
}