package com.example.kyrs.presentation.plans

import com.example.kyrs.data.entity.Event
import com.example.kyrs.presentation.base.BaseView

/**
 * Project HelloMate
 * Package com.example.kyrs.presentation.plans
 *
 *
 *
 * Created by Timur Badretdinov (aka timurbadretdinov) 2019-07-10
 */
interface PlansView : BaseView {

    fun showEvents(events: List<Event>?)
    fun updateEvents(events: List<Event>?)

    fun hideLoader()

    fun openEventActivity(event: Event)
    fun openNewEventActivity()
}