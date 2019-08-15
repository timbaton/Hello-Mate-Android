package com.example.kyrs.data.entity.request

/**
 * Project HelloMate
 * Package com.example.kyrs.data.entity.request
 *
 *
 *
 * Created by Timur Badretdinov (aka timurbadretdinov) 2019-07-09
 */
data class NewEventRequest (
    var title: String,
    var description: String,
    var location: String,
    var date: String
)