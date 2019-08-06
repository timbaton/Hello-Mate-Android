package com.example.kyrs.ui.plans

import com.arellomobile.mvp.presenter.InjectPresenter
import com.arellomobile.mvp.presenter.ProvidePresenter
import com.example.kyrs.R
import com.example.kyrs.data.entity.response.ProfileResponse
import com.example.kyrs.di.Scopes
import com.example.kyrs.presentation.profile.ProfilePresenter
import com.example.kyrs.presentation.profile.ProfileView
import com.example.kyrs.ui.base.BaseFragment
import kotlinx.android.synthetic.main.fragment_profile.*
import toothpick.Toothpick

/**
 * Project HelloMate
 * Package com.example.kyrs.ui.plans
 *
 *
 *
 * Created by Timur Badretdinov (aka timurbadretdinov) 2019-07-10
 * Copyright © 2018 SuperEgo. All rights reserved.
 */
class ProfileFragment : BaseFragment(), ProfileView{

    override val layoutRes: Int = R.layout.fragment_profile

    @InjectPresenter
    lateinit var presenter: ProfilePresenter

    @ProvidePresenter
    fun providePresenter(): ProfilePresenter {
        return Toothpick.openScope(Scopes.Server)
            .getInstance(ProfilePresenter::class.java)
    }

    override fun fillProfileData(data: ProfileResponse?) {
        tvName.text = data?.name + " " + data?.surname
        tvMail.text = data?.mail
        tvPhone.text = data?.phone
    }
}