package com.example.kyrs.ui.map

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.os.Bundle
import android.util.Log
import androidx.annotation.NonNull
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import com.arellomobile.mvp.presenter.InjectPresenter
import com.arellomobile.mvp.presenter.ProvidePresenter
import com.example.kyrs.R
import com.example.kyrs.di.Scopes
import com.example.kyrs.presentation.map.MapPresenter
import com.example.kyrs.presentation.map.MapView
import com.example.kyrs.ui.base.BaseActivity
import com.example.kyrs.ui.new_event.NewEventActivity
import com.google.android.gms.location.LocationServices
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions
import kotlinx.android.synthetic.main.toolbar.*
import toothpick.Toothpick


class MapActivity : BaseActivity(), OnMapReadyCallback, MapView {

    override var res: Int? = R.layout.activity_map

    private lateinit var mMap: GoogleMap

    private var PERMISSIONS_REQUEST_ACCESS_FINE_LOCATION = 123


    companion object {
        fun getIntent(context: Context): Intent {
            return Intent(context, MapActivity::class.java)
        }
    }

    @InjectPresenter
    lateinit var presenter: MapPresenter

    @ProvidePresenter
    fun providePresenter(): MapPresenter {
        return Toothpick.openScope(Scopes.Server)
            .getInstance(MapPresenter::class.java)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val mapFragment = supportFragmentManager
            .findFragmentById(R.id.map) as SupportMapFragment
        mapFragment.getMapAsync(this)

        btnReady.setOnClickListener {
            presenter.onReadyClicked()
        }

        btnBack.setOnClickListener {
            presenter.onBackClicked()
        }
    }

    override fun onMapReady(googleMap: GoogleMap) {
        mMap = googleMap
        presenter.onMapReady()

        mMap.setOnMapClickListener {
            presenter.onMapClicked(it)
        }
    }

    override fun askPermission() {
        val checkSelfPermission =
            ContextCompat.checkSelfPermission(this.applicationContext, android.Manifest.permission.ACCESS_FINE_LOCATION)
        if (checkSelfPermission == PackageManager.PERMISSION_GRANTED) {
            presenter.mLocationPermissionGranted = true
        } else {
            ActivityCompat.requestPermissions(
                this,
                arrayOf(android.Manifest.permission.ACCESS_FINE_LOCATION),
                PERMISSIONS_REQUEST_ACCESS_FINE_LOCATION
            )
        }
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        @NonNull permissions: Array<String>,
        @NonNull grantResults: IntArray
    ) {
        when (requestCode) {
            PERMISSIONS_REQUEST_ACCESS_FINE_LOCATION -> {
                // If request is cancelled, the result arrays are empty.
                if (grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    presenter.onPermissionGranted()
                }
            }
        }
    }

    override fun getUserLocation() {
        try {
            val locationResult = LocationServices.getFusedLocationProviderClient(this).lastLocation
            locationResult.addOnCompleteListener {
                if (it.isSuccessful) {
                    presenter.onGotLocation(it.result!!)
                } else {
                    mMap.uiSettings.isMyLocationButtonEnabled = false
                }
            }
        } catch (e: SecurityException) {
            Log.e("Exception: %s", e.message)
        }
    }

    override fun showLocationButton() {
        try {
            mMap.isMyLocationEnabled = true
            mMap.uiSettings.isMyLocationButtonEnabled = true
        } catch (e: SecurityException) {
            Log.e("Exception: %s", e.message)
        }
    }

    override fun moveCamera(curLocation: LatLng) {
        mMap.moveCamera(CameraUpdateFactory.newLatLng(curLocation))
    }

    override fun putMarker(position: LatLng) {
        mMap.clear()
        mMap.addMarker(
            MarkerOptions()
                .position(position ?: LatLng(-34.0, 151.0))
                .draggable(true)
                .title("Location")
        )
    }

    override fun openPrevActivity(location: LatLng) {
        val intent = Intent()
        intent.putExtra(NewEventActivity.KEY_LOCATION, location)
        setResult(Activity.RESULT_OK, intent)

        finish()
    }
}
