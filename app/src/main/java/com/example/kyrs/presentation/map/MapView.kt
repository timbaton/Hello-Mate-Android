package com.example.kyrs.presentation.map

import com.example.kyrs.presentation.base.BaseView
import com.google.android.gms.maps.model.LatLng

/**
 * Project HelloMate
 * Package com.example.kyrs.presentation.map
 *
 *
 *
 * Created by Timur Badretdinov (aka timurbadretdinov) 2019-08-13
 */
interface MapView : BaseView {

    fun moveCamera(curLocation: LatLng)
    fun putMarker(position: LatLng)

    fun askPermission()

    fun getUserLocation()
    fun showLocationButton()

    fun openPrevActivity(location: LatLng)
    fun onBackPressed()
}