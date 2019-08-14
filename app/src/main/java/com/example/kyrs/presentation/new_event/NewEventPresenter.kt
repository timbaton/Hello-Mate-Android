package com.example.kyrs.presentation.new_event

import android.app.Activity
import android.app.DatePickerDialog
import android.content.Intent
import com.arellomobile.mvp.InjectViewState
import com.example.kyrs.data.entity.Location
import com.example.kyrs.data.entity.request.NewEventRequest
import com.example.kyrs.data.repository.EventRepository
import com.example.kyrs.presentation.base.BasePresenter
import com.example.kyrs.ui.new_event.NewEventActivity
import com.google.android.gms.maps.model.LatLng
import java.util.*
import javax.inject.Inject

/**
 * Project HelloMate
 * Package com.example.kyrs.presentation.new_event
 *
 *
 *
 * Created by Timur Badretdinov (aka timurbadretdinov) 2019-08-12
 * Copyright © 2018 SuperEgo. All rights reserved.
 */
@InjectViewState
class NewEventPresenter @Inject constructor(
    private var eventRepository: EventRepository
) : BasePresenter<NewEventView>() {

    var dateAndTime = Calendar.getInstance()
    var location: Location? = null

    override fun onFirstViewAttach() {
        super.onFirstViewAttach()
        viewState.showDate(dateAndTime)
    }

    // установка обработчика выбора даты
    var dateSetListener = DatePickerDialog.OnDateSetListener { _, year, month, dayOfMonth ->
        dateAndTime.set(Calendar.YEAR, year)
        dateAndTime.set(Calendar.MONTH, month)
        dateAndTime.set(Calendar.DAY_OF_MONTH, dayOfMonth)
        viewState.showDate(dateAndTime)
    }

    fun onDateClicked() {
        val year = dateAndTime.get(Calendar.YEAR)
        val month = dateAndTime.get(Calendar.MONTH)
        val date = dateAndTime.get(Calendar.DAY_OF_MONTH)
        viewState.showDateDialog(year, month, date, dateSetListener)
    }

    fun onOpenMapClicked() {
        viewState.openMapActivity()
    }

    fun onCancelClicked() {
        viewState.onBackPressed()
    }

    fun onReadyClicked(title: String, hours: Int, minute: Int, description: String) {
        dateAndTime.apply {
            set(Calendar.HOUR, hours)
            set(Calendar.MINUTE, minute)
        }

        val timeStamp = Date(dateAndTime.time.time)
        val time = timeStamp.time.toString()

        eventRepository.addEvent(
            NewEventRequest(
                title = title,
                description = description,
                location = location!!.lat.toString() + " " + location!!.lng.toString(),
                date = time
            )
        ).subscribe({
            viewState.showMessage(it.id.toString())
        }, {
            viewState.showMessage(it.message.toString())
        }).connect()
    }

    fun onActivityResult(resultCode: Int, data: Intent?) {
        // Make sure the request was successful
        if (resultCode == Activity.RESULT_OK) {
            val location: LatLng? = data?.extras?.getParcelable(NewEventActivity.KEY_LOCATION)
            val locationString = location?.latitude.toString() + ", " + location?.longitude.toString()

            viewState.showLocation(locationString)

            this.location = Location(location?.latitude!!, location.longitude)
        }
    }
}