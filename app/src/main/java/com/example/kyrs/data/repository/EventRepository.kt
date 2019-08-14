package com.example.kyrs.data.repository

import com.example.kyrs.data.entity.Event
import com.example.kyrs.data.entity.request.NewEventRequest
import com.example.kyrs.data.network.HelloMateApi
import com.example.kyrs.data.sharedPref.AuthHolder
import io.reactivex.Single
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class EventRepository @Inject constructor(
    private val api: HelloMateApi,
    private val authHolder: AuthHolder
) {
    fun getUsersEvents(): Single<List<Event>> {
        return api.getUserEvents(authHolder.userId)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
    }

    fun getFutureEvents(): Single<List<Event>> {
        return api.getAllEvents(true)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
    }

    fun register(id: Int): Single<Event>{
        return api.eventRegister(id, authHolder.userId)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
    }

    fun unsubscribe(id: Int): Single<Event> {
        return api.eventUnsubscribe(id, authHolder.userId)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
    }

    fun addEvent(event: NewEventRequest): Single<Event> {
        return api.addEvent(event, authHolder.userId)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
    }
}
