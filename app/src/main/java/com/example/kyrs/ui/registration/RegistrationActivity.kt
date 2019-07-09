package com.example.kyrs.ui.registration

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.os.PersistableBundle
import com.arellomobile.mvp.presenter.InjectPresenter
import com.arellomobile.mvp.presenter.ProvidePresenter
import com.example.kyrs.R
import com.example.kyrs.di.Scopes
import com.example.kyrs.presentation.registration.RegistrationPresenter
import com.example.kyrs.presentation.registration.RegistrationView
import com.example.kyrs.ui.base.BaseActivity
import kotlinx.android.synthetic.main.toolbar_back.*
import toothpick.Toothpick

/**
 * Project HelloMate
 * Package com.example.kyrs.ui.registration
 *
 *
 *
 * Created by Timur Badretdinov (aka timurbadretdinov) 2019-07-09
 * Copyright © 2018 SuperEgo. All rights reserved.
 */
class RegistrationActivity : BaseActivity(), RegistrationView {

    override var res: Int? = R.layout.activity_registration

    companion object {

        fun getIntent(context: Context): Intent {
            return Intent(context, RegistrationActivity::class.java)
        }
    }

    @InjectPresenter
    lateinit var presenter: RegistrationPresenter

    @ProvidePresenter
    fun providePresenter(): RegistrationPresenter {
        return Toothpick.openScope(Scopes.Server)
            .getInstance(RegistrationPresenter::class.java)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        backButton.setOnClickListener { presenter.onBackCliecked() }
    }

    override fun back() {
        finish()
    }
}