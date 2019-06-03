package com.example.kyrs

import android.app.Application
import com.example.kyrs.di.Scopes
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
        } else {
//            Toothpick.setConfiguration(Configuration.forProduction().disableReflection())
//            FactoryRegistryLocator.setRootRegistry(FactoryRegistry())
//            MemberInjectorRegistryLocator
//                .setRootRegistry(MemberInjectorRegistry())
        }

        val scope = Toothpick.openScope(Scopes.Server)
        scope.installModules(ServerModule(BuildConfig.API_URL))
        Toothpick.inject(this, scope)
    }
}
