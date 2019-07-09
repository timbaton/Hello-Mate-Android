package com.example.kyrs

import android.app.Application
import com.example.kyrs.di.Scopes
import com.example.kyrs.di.modules.AppModule
import com.example.kyrs.di.modules.ServerModule
import toothpick.Toothpick
import toothpick.configuration.Configuration
//import toothpick.registries.FactoryRegistryLocator
//import toothpick.registries.MemberInjectorRegistryLocator

/**
 * Project Kyrs
 * Package com.example.kyrs
 *
 *
 *
 *
 *
 *
 * Created by Timur Badretdinov (aka timurbadretdinov) 2019-05-29
 * Copyright © 2018 SuperEgo. All rights reserved.
 */
class App : Application() {


    override fun onCreate() {
        super.onCreate()

        if (BuildConfig.DEBUG) {
            Toothpick.setConfiguration(Configuration.forDevelopment().preventMultipleRootScopes())
        }

        val appScope = Toothpick.openScope(Scopes.App)
        appScope.installModules(AppModule(this))

        val serverScope = Toothpick.openScopes(Scopes.App, Scopes.Server)
        serverScope.installModules(ServerModule(BuildConfig.API_URL))

//        Toothpick.inject(this, scope)
    }
}
