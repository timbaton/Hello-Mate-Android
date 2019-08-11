package com.example.kyrs.presentation.search

import com.example.kyrs.data.entity.Event
import com.example.kyrs.presentation.base.BaseView

/**
 * Project HelloMate
 * Package com.example.kyrs.presentation.search
 *
 *
 *
 * Created by Timur Badretdinov (aka timurbadretdinov) 2019-08-10
 * Copyright © 2018 SuperEgo. All rights reserved.
 */
interface SearchView : BaseView {

    fun showEvents(events: List<Event>)

    fun openEventActivity(event: Event)

}