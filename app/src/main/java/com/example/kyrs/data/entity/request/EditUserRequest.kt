package com.example.kyrs.data.entity.request

/**
 * Project HelloMate
 * Package com.example.kyrs.data.entity.request
 *
 *
 *
 * Created by Timur Badretdinov (aka timurbadretdinov) 2019-07-09
 */
data class EditUserRequest(
    val id: Int = 0,
    val name: String? = null,
    val surname: String? = null,
    val mail: String? = null,
    val phone: String? = null
)