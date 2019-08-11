package com.example.kyrs.presentation.event

import com.arellomobile.mvp.InjectViewState
import com.example.kyrs.data.entity.Event
import com.example.kyrs.di.ImagePath
import com.example.kyrs.presentation.base.BasePresenter
import javax.inject.Inject

/**
 * Project HelloMate
 * Package com.example.kyrs.presentation.event
 *
 *
 *
 * Created by Timur Badretdinov (aka timurbadretdinov) 2019-08-11
 * Copyright © 2018 SuperEgo. All rights reserved.
 */
@InjectViewState
class EventPresenter @Inject constructor(
    @ImagePath private var imagePath: String
): BasePresenter<EventView>() {

    lateinit var event: Event

    override fun onFirstViewAttach() {
        viewState.showEvent(event, imagePath)
    }

    fun onParticipantClicked(user_id: Int) {
        viewState.showMessage(user_id.toString())
    }
}