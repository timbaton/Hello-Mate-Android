package com.example.kyrs.data.entity

import com.example.kyrs.data.entity.response.User

/**
 * Project HelloMate
 * Package com.example.kyrs.data.entity
 *
 *
 *
 * Created by Timur Badretdinov (aka timurbadretdinov) 2019-08-07
 */
data class Event(
    val id: Long,
    val title: String,
    val description: String,
    val location: Location,
    val date: String,
    val images: List<Image>,
    val owner: User,
    val participants: List<User>
)