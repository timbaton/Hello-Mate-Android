package com.example.kyrs.presentation.event

import com.example.kyrs.data.entity.Event
import com.example.kyrs.presentation.base.BaseView
import com.google.android.gms.maps.model.LatLng

/**
 * Project HelloMate
 * Package com.example.kyrs.presentation.event
 *
 *
 *
 * Created by Timur Badretdinov (aka timurbadretdinov) 2019-08-11
 * Copyright © 2018 SuperEgo. All rights reserved.
 */
interface EventView : BaseView {

    fun showEvent(event: Event, imagePath: String)
    fun showEventLocation(location: LatLng)

    fun openProfile(userId: Int)

    fun setButtonUnregister()
    fun setButtonRegister()
    fun setButtonDelete()

    fun openDialogSuccess()

    fun finish()
    fun onBackPressed()
}