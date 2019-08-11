package com.example.kyrs.ui.event

import android.content.Context
import android.content.Intent
import com.arellomobile.mvp.presenter.InjectPresenter
import com.arellomobile.mvp.presenter.ProvidePresenter
import com.example.kyrs.R
import com.example.kyrs.di.Scopes
import com.example.kyrs.presentation.event.EventPresenter
import com.example.kyrs.presentation.event.EventView
import com.example.kyrs.ui.base.BaseActivity
import toothpick.Toothpick

/**
 * Project HelloMate
 * Package com.example.kyrs.ui.event
 *
 *
 *
 * Created by Timur Badretdinov (aka timurbadretdinov) 2019-08-11
 * Copyright © 2018 SuperEgo. All rights reserved.
 */
class EventActivity : BaseActivity(), EventView {

    override var res: Int? = R.layout.activity_event

    companion object {
        fun getIntent(context: Context): Intent {
            val intent = Intent(context, EventActivity::class.java)
            return intent
        }
    }

    @InjectPresenter
    lateinit var presenter: EventPresenter

    @ProvidePresenter
    fun providePresenter(): EventPresenter {
        return Toothpick.openScope(Scopes.Server)
            .getInstance(EventPresenter::class.java)
    }
}