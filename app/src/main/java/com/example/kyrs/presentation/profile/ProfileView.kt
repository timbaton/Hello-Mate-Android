package com.example.kyrs.presentation.profile

import com.example.kyrs.data.entity.response.ProfileResponse
import com.example.kyrs.presentation.base.BaseView

/**
 * Project HelloMate
 * Package com.example.kyrs.presentation.profile
 *
 *
 *
 * Created by Timur Badretdinov (aka timurbadretdinov) 2019-08-06
 */
interface ProfileView : BaseView {

    fun fillProfileData(data: ProfileResponse?, serverPath: String)

    fun openEditProfileActivity()
    fun openLoginActivity()

    fun onBackPressed()

}