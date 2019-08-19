package com.example.kyrs.data.entity.request

/**
 * Project HelloMate
 * Package com.example.kyrs.data.entity.request
 *
 * Created by Timur Badretdinov (aka timurbadretdinov) 2019-08-19
 *
 */
data class RegisterRequest(
    var login: String,
    var password: String,
    var name: String,
    var surName: String
)