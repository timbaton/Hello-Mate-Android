package com.example.kyrs.utils

import android.content.Context
import android.widget.Toast

/**
 * Project Kyrs
 * Package com.example.kyrs.utils
 *
 *
 *
 * Created by Timur Badretdinov (aka timurbadretdinov) 2019-06-02
 * Copyright © 2018 SuperEgo. All rights reserved.
 */

fun Context.showToast(message: String, duration: Int = Toast.LENGTH_SHORT,
                      otherContext: Context? = null) {
    Toast.makeText(otherContext ?: this, message, duration).show()
}