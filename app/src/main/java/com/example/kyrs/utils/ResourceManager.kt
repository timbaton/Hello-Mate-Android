package com.example.kyrs.utils

import android.content.Context
import javax.inject.Inject

/**
 * Project HelloMate
 * Package com.example.kyrs.utils
 *
 * Created by Timur Badretdinov (aka timurbadretdinov) 2019-08-16
 *
 */
class ResourceManager @Inject constructor(private val context: Context) {

    fun getString(id: Int) = context.getString(id)
}