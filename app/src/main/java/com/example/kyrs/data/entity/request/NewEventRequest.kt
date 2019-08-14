package com.example.kyrs.data.entity.request

import com.example.kyrs.data.entity.Location

/**
 * Project HelloMate
 * Package com.example.kyrs.data.entity.request
 *
 *
 *
 * Created by Timur Badretdinov (aka timurbadretdinov) 2019-07-09
 * Copyright © 2018 SuperEgo. All rights reserved.
 */
data class NewEventRequest (
    var title: String,
    var description: String,
    var location: String,
    var date: String
)