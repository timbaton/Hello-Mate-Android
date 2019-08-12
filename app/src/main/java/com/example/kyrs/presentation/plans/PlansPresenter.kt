package com.example.kyrs.presentation.plans

import com.arellomobile.mvp.InjectViewState
import com.example.kyrs.data.entity.Event
import com.example.kyrs.data.repository.EventRepository
import com.example.kyrs.presentation.base.BasePresenter
import javax.inject.Inject

/**
 * Project HelloMate
 * Package com.example.kyrs.presentation.plans
 *
 *
 *
 * Created by Timur Badretdinov (aka timurbadretdinov) 2019-07-10
 * Copyright © 2018 SuperEgo. All rights reserved.
 */
@InjectViewState
class PlansPresenter @Inject constructor(
    private val eventRepository: EventRepository
): BasePresenter<PlansView>() {

    override fun onFirstViewAttach() {
        super.onFirstViewAttach()

        loadPlans()
    }

    private fun loadPlans() {
        eventRepository.getUsersEvents()
            .subscribe({
                viewState.showEvents(it)
            },{
                viewState.showMessage(it.message.toString())
            }).connect()
    }

    fun onEventClicked(event: Event) {
        viewState.openEventActivity(event)
    }

    fun onRefreshCalled() {
        eventRepository.getUsersEvents()
            .doAfterTerminate {
                viewState.hideLoader()
            }
            .subscribe({
                viewState.updateEvents(it)
            },{
                viewState.showMessage(it.message.toString())
            }).connect()
    }

    fun onAddButtonClicked() {
        viewState.openNewEventActivity()
    }
}