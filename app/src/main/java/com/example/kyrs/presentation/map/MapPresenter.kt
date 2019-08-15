package com.example.kyrs.presentation.map

import android.location.Location
import com.arellomobile.mvp.InjectViewState
import com.example.kyrs.presentation.base.BasePresenter
import com.google.android.gms.maps.model.LatLng
import javax.inject.Inject

/**
 * Project HelloMate
 * Package com.example.kyrs.presentation.map
 *
 *
 *
 * Created by Timur Badretdinov (aka timurbadretdinov) 2019-08-13
 */
@InjectViewState
class MapPresenter @Inject constructor(

) : BasePresenter<MapView>() {

    var mLocationPermissionGranted = false
    var location: LatLng = LatLng(-34.0, 151.0)

    override fun onFirstViewAttach() {
        super.onFirstViewAttach()

        if (!mLocationPermissionGranted) {
            viewState.askPermission()
        }
    }

    fun onReadyClicked() {
        viewState.openPrevActivity(location)
    }

    fun onMapReady() {
        if (mLocationPermissionGranted) {
            viewState.getUserLocation()
        } else {
            viewState.askPermission()
        }
    }

    fun onPermissionGranted() {
        mLocationPermissionGranted = true

        viewState.showLocationButton()

        viewState.getUserLocation()
    }

    fun onGotLocation(result: Location) {
        location = LatLng(result.latitude, result.longitude)

        viewState.showLocationButton()

        viewState.moveCamera(location)
        viewState.putMarker(location)
    }

    fun onMapClicked(location: LatLng) {
        this.location = location
        viewState.moveCamera(location)
        viewState.putMarker(location)
    }

    fun onBackClicked() {
        viewState.onBackPressed()
    }
}