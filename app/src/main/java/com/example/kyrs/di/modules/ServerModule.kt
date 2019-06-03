package com.example.kyrs.di.modules

import com.example.kyrs.data.network.HelloMateApi
import com.example.kyrs.di.ServerPath
import com.example.kyrs.di.providers.ApiProvider
import com.example.kyrs.di.providers.OkHttpClientProvider
import okhttp3.OkHttpClient
import toothpick.config.Module

/**
 * Project Kyrs
 * Package com.example.kyrs.di
 *
 *
 *
 * Created by Timur Badretdinov (aka timurbadretdinov) 2019-05-29
 * Copyright © 2018 SuperEgo. All rights reserved.
 */
class ServerModule (baseUrl: String): Module() {

    init {
        bind(String::class.java).withName(ServerPath::class.java).toInstance(baseUrl)

//        Retrofit
        bind(OkHttpClient::class.java).toProvider(OkHttpClientProvider::class.java).providesSingletonInScope()
        bind(HelloMateApi::class.java).toProvider(ApiProvider::class.java).providesSingletonInScope()
    }
}