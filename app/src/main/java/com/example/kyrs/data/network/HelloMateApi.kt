package com.example.kyrs.data.network

import com.example.kyrs.data.entity.Event
import com.example.kyrs.data.entity.request.LoginRequest
import com.example.kyrs.data.entity.request.NewEventRequest
import com.example.kyrs.data.entity.response.LoginResponse
import com.example.kyrs.data.entity.response.ProfileResponse
import com.example.kyrs.data.entity.response.User
import io.reactivex.Single
import okhttp3.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Query


/**
 * Project Kyrs
 * Package com.example.kyrs.data
 *
 *
 *
 * Created by Timur Badretdinov (aka timurbadretdinov) 2019-06-01
 * Copyright © 2018 SuperEgo. All rights reserved.
 */
interface HelloMateApi {

    @GET("/rest/users")
    fun getUsers(): Single<User>

    @GET("/rest/users/")
    fun getProfile(@Query(value = "id") id: Int): Single<ProfileResponse>

    @POST("/rest/login")
    fun login(@Body loginRequest: LoginRequest): Single<LoginResponse>

    @POST("/rest/register")
    fun register(@Body loginRequest: LoginRequest): Single<LoginResponse>

    @GET("/rest/event")
    fun getUserEvents(@Query(value = "user_id") id: Int): Single<List<Event>>

    @GET("/rest/event")
    fun getAllEvents(@Query(value = "isFuture") isFuture: Boolean): Single<List<Event>>

    @POST("/rest/event_register")
    fun eventRegister(@Query(value = "event_id") event_id: Int, @Query(value = "user_id") user_id: Int): Single<Event>

    @POST("/rest/event_unsubscribe")
    fun eventUnsubscribe(@Query(value = "event_id") event_id: Int, @Query(value = "user_id") user_id: Int): Single<Event>

    @POST("/rest/event")
    fun addEvent(@Body event: NewEventRequest,@Query(value = "user_id") id: Int): Single<Event>

    @POST("/rest/event_delete")
    fun deleteEvent(@Query(value = "event_id") id: Int): Single<Boolean>
}