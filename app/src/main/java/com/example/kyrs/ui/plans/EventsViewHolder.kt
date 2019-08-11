package com.example.kyrs.ui.plans

import android.view.View
import com.example.kyrs.data.entity.Event
import com.example.kyrs.presentation.base.BaseListViewHolder
import kotlinx.android.synthetic.main.item_event.view.*
import java.text.ParseException
import java.text.SimpleDateFormat
import java.util.*
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

    override fun bind(item: Event) {
        itemView.tvDescription.text = item.description

        itemView.tvTime.text = getTime(item)
    }

    private fun getTime(item: Event): String {

        var dateString = item.date
        var date: Date? = null
        val dateFromString = SimpleDateFormat("yyyy-MM-dd HH:mm:ss.S")

        dateFromString.setLenient(false)
        try {
            date = dateFromString.parse(dateString)
        } catch (e: ParseException) {
            e.printStackTrace()
        }

//        val date2 = timestamp.time
        val date1 = Date().time
        val date2 = date!!.time


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
        return "$hours hours$minutes min"
    }
}