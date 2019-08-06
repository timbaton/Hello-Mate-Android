package com.example.kyrs.data.entity.response

import com.example.kyrs.data.entity.Image
import com.google.gson.annotations.SerializedName

data class ProfileResponse(
    private val id: Int = 0,
    val name: String? = null,
    val surname: String? = null,
    val mail: String? = null,
    val phone: String? = null,

    @SerializedName("avatar")
    val avatar: Image? = null
)
