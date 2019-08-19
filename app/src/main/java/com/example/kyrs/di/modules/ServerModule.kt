package com.example.kyrs.di.modules

import com.example.kyrs.data.network.HelloMateApi
import com.example.kyrs.data.repository.AuthRepository
import com.example.kyrs.data.repository.EventRepository
import com.example.kyrs.data.repository.ProfileRepository
import com.example.kyrs.di.ImagePath
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
 */
class ServerModule(baseUrl: String) : Module() {

    init {

        bind(String::class.java).withName(ServerPath::class.java).toInstance(baseUrl)
        bind(String::class.java).withName(ImagePath::class.java).toInstance("$baseUrl/uploads/")

        // Retrofit
        bind(OkHttpClient::class.java).toProvider(OkHttpClientProvider::class.java).providesSingletonInScope()
        bind(HelloMateApi::class.java).toProvider(ApiProvider::class.java).providesSingletonInScope()

        // Repositories
        bind(AuthRepository::class.java).singletonInScope()
        bind(EventRepository::class.java).singletonInScope()
        bind(ProfileRepository::class.java).singletonInScope()
    }
}