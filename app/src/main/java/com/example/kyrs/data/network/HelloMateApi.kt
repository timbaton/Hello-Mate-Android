package com.example.kyrs.data.network

import com.example.kyrs.entity.User
import io.reactivex.Single
import retrofit2.http.GET


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

    @GET("/users")
    fun getUsers(): Single<List<User>>
}