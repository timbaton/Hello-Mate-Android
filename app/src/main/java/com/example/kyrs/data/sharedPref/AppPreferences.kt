package com.example.kyrs.data.sharedPref

import android.content.Context
import javax.inject.Inject

/**
 * Project HelloMate
 * Package com.example.kyrs.data.sharedPref
 *
 *
 *
 * Created by Timur Badretdinov (aka timurbadretdinov) 2019-07-09
 * Copyright © 2018 SuperEgo. All rights reserved.
 */
class AppPreferences @Inject constructor(private val context: Context) : AuthHolder {

    private val prefsName = "authData"
    private val keyUserId = "user_id"
    private val keyUserToken = "user_token"

    override var userId: Int
        get() = getPrefs().getInt(keyUserId, 0)
        set(value) {
            getPrefs().edit().putInt(keyUserId, value).apply()
        }
    override var token: String
        get() = getPrefs().getString(keyUserToken, "")!!
        set(value) {
            getPrefs().edit().putString(keyUserToken, value).apply()
        }

    private fun getPrefs() = context.getSharedPreferences(prefsName, Context.MODE_PRIVATE)

}