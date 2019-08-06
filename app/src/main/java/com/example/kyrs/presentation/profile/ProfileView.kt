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
 * Copyright © 2018 SuperEgo. All rights reserved.
 */
interface ProfileView : BaseView {

    fun fillProfileData(data: ProfileResponse?)

}