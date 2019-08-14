package com.example.kyrs.ui.EditProfile

import com.arellomobile.mvp.presenter.InjectPresenter
import com.arellomobile.mvp.presenter.ProvidePresenter
import com.example.kyrs.R
import com.example.kyrs.di.Scopes
import com.example.kyrs.presentation.edit_profile.EditProfilePresenter
import com.example.kyrs.presentation.edit_profile.EditProfileView
import com.example.kyrs.ui.base.BaseActivity
import toothpick.Toothpick
import java.util.*

/**
 * Project HelloMate
 * Package com.example.kyrs.ui.EditProfile
 *
 *
 *
 * Created by Timur Badretdinov (aka timurbadretdinov) 2019-08-14
 * Copyright © 2018 SuperEgo. All rights reserved.
 */
class EditProfileActivity : BaseActivity(), EditProfileView {

    override var res: Int? = R.layout.activity_edit_profile

    @InjectPresenter
    lateinit var presenter: EditProfilePresenter

    @ProvidePresenter
    fun providePresenter(): EditProfilePresenter {
        return Toothpick.openScope(Scopes.Server)
            .getInstance(EditProfilePresenter::class.java)
    }


}