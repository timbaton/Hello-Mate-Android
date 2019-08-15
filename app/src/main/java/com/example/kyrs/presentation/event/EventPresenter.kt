package com.example.kyrs.presentation.event

import com.arellomobile.mvp.InjectViewState
import com.example.kyrs.data.entity.Event
import com.example.kyrs.data.repository.EventRepository
import com.example.kyrs.data.sharedPref.AuthHolder
import com.example.kyrs.di.ImagePath
import com.example.kyrs.presentation.base.BasePresenter
import com.google.android.gms.maps.model.LatLng
import javax.inject.Inject

/**
 * Project HelloMate
 * Package com.example.kyrs.presentation.event
 *
 *
 *
 * Created by Timur Badretdinov (aka timurbadretdinov) 2019-08-11
 */
@InjectViewState
class EventPresenter @Inject constructor(
    @ImagePath private var imagePath: String,
    private var eventRepository: EventRepository,
    private var authHolder: AuthHolder
) : BasePresenter<EventView>() {

    lateinit var event: Event

    override fun onFirstViewAttach() {
        viewState.showEvent(event, imagePath)

        var isRegistered = false
        var isOwner = false

        event.participants.forEach {
            if (it.id == authHolder.userId) {
                isRegistered = true
            }
        }

        if (event.owner.id == authHolder.userId) {
            isOwner = true
        }

        if (isOwner) {
            viewState.setButtonDelete()
        }

        if (isRegistered) {
            viewState.setButtonUnregister()
        }
    }

    fun onParticipantClicked(user_id: Int) {
        viewState.openProfile(user_id)
    }

    fun onRegisterClicked() {
        eventRepository.register(event.id.toInt())
            .subscribe({
                viewState.openDialogSuccess()
            }, {
                viewState.showMessage(it.message.toString())
            }).connect()
    }

    fun onUnsubscribeClicked() {
        eventRepository.unsubscribe(event.id.toInt())
            .doAfterTerminate {
                viewState.setButtonRegister()
            }
            .subscribe({
                viewState.openDialogSuccess()
            }, {
                viewState.showMessage(it.message.toString())
            }).connect()
    }

    fun onDeleteClicked() {
        eventRepository.delete(event.id.toInt())
            .subscribe({
                viewState.openDialogSuccess()
            }, {
                viewState.showMessage(it.message.toString())
            }).connect()

    }

    fun onCloseSuccessDialog() {
        viewState.finish()
    }

    fun onBackPressed() {
        viewState.onBackPressed()
    }

    fun onOpenProfileClicked() {
        viewState.openProfile(event.owner.id)
    }

    fun onMapReady() {
        viewState.showEventLocation(LatLng(event.location.lat, event.location.lng))
    }
}