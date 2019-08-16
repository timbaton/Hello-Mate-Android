package com.example.kyrs.data.entity.response

import com.example.kyrs.data.entity.Image
import com.google.gson.annotations.SerializedName

data class ProfileResponse(
    val id: Int = 0,
    val login: String? = null,
    val name: String? = null,
    val surname: String? = null,
    val mail: String? = null,
    val phone: String? = null,

    @SerializedName("avatar")
    val avatar: Image? = null
)
