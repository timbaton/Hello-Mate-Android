package com.example.kyrs.data.entity.response

import com.example.kyrs.data.entity.Image
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

/**
 * Project Kyrs
 * Package com.example.kyrs.data.entity
 *
 *
 *
 * Created by Timur Badretdinov (aka timurbadretdinov) 2019-06-01
 */

data class User(
    var id: Int,
    var name: String,
    var surname: String,
    var login: String,
    var password: String,
    var avatar: Image,
    var mail: String,
    var phone: String,
    var hobbies: String,
    var age: Int,

    @SerializedName("tokens")
    @Expose
    var tokens: List<String>?
)