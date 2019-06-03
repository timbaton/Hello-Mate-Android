package com.example.kyrs.di.providers

import com.example.kyrs.data.network.HelloMateApi
import com.example.kyrs.di.ServerPath
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Inject
import javax.inject.Provider

/**
 * Project Kyrs
 * Package com.example.kyrs.di.providers
 *
 *
 *
 * Created by Timur Badretdinov (aka timurbadretdinov) 2019-06-02
 * Copyright © 2018 SuperEgo. All rights reserved.
 */
class ApiProvider @Inject constructor(
    @ServerPath private val serverUri: String,
    private var client: OkHttpClient
) : Provider<HelloMateApi> {

    override fun get(): HelloMateApi =
        Retrofit.Builder()
            .baseUrl(serverUri)
            .client(client)
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(HelloMateApi::class.java)

}