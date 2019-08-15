package com.example.kyrs.di.modules

import android.content.Context
import com.example.kyrs.data.sharedPref.AppPreferences
import com.example.kyrs.data.sharedPref.AuthHolder
import toothpick.config.Module

/**
 * Project HelloMate
 * Package com.example.kyrs.di.modules
 *
 *
 *
 * Created by Timur Badretdinov (aka timurbadretdinov) 2019-07-09
 */
class AppModule(context: Context) : Module() {
    init {
        bind(Context::class.java).toInstance(context)

        bind(AuthHolder::class.java).to(AppPreferences::class.java).singletonInScope()
    }
}