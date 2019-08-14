package com.example.kyrs.ui.plans

import android.view.View
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.CircleCrop
import com.example.kyrs.data.entity.Event
import com.example.kyrs.di.ImagePath
import com.example.kyrs.di.Scopes
import com.example.kyrs.presentation.base.BaseListViewHolder
import kotlinx.android.synthetic.main.item_event.view.*
import toothpick.Scope
import toothpick.Toothpick
import java.text.ParseException
import java.text.SimpleDateFormat
import java.util.*
import javax.inject.Inject
import kotlin.math.abs
import kotlin.math.floor


/**
 * Project HelloMate
 * Package com.example.kyrs.ui.plans
 *
 *
 *
 * Created by Timur Badretdinov (aka timurbadretdinov) 2019-08-07
 * Copyright © 2018 SuperEgo. All rights reserved.
 */
class EventsViewHolder(itemView: View) : BaseListViewHolder<Event>(itemView) {

    @Inject
    @field:ImagePath
    lateinit var serverPath: String// named binding, with name "@my.MyAnnotation"

    override fun bind(item: Event) {

//        TODO: fix this hardcode
        serverPath = Toothpick.openScope(Scopes.Server).getInstance(String::class.java, "com.example.kyrs.di.ImagePath")

        itemView.tvTitle.text = item.title
        itemView.tvTime.text = getTime(item)


        val url = "$serverPath${item.owner.avatar.path}"
        Glide.with(itemView)
            .load(url)
            .transform(CircleCrop())
            .into(itemView.ivAvatar)
    }

    private fun getTime(item: Event): String {

        var dateString = item.date

//        val date2 = timestamp.time
        val date1 = Date().time
        val date2 = dateString.toLong()


        val res = abs(date1 - date2) / 1000

        // get total days between two dates
        with(floor((res / 86400).toDouble())) {
            if (this > 0) {
                dateString = "$this days"
                return dateString
            }
        }

        // get hours
        val hours = floor((res / 3600).toDouble()) % 24

        val minutes = floor((res / 60).toDouble()) % 60
        return "$hours hours $minutes min"
    }
}