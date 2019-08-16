package com.example.kyrs.ui.plans

import android.view.View
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.CircleCrop
import com.example.kyrs.data.entity.Event
import com.example.kyrs.di.ImagePath
import com.example.kyrs.di.Scopes
import com.example.kyrs.presentation.base.BaseListViewHolder
import com.example.kyrs.utils.loadImage
import kotlinx.android.synthetic.main.item_event.view.*
import toothpick.Toothpick
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
 */
class EventsViewHolder(itemView: View) : BaseListViewHolder<Event>(itemView) {

    @Inject
    @field: ImagePath
    lateinit var imagePath: String

    override fun bind(item: Event) {

        Toothpick.inject(this, Toothpick.openScope(Scopes.Server))

        itemView.tvTitle.text = item.title
        itemView.tvTime.text = getTime(item)


        val url = "$imagePath${item.owner.avatar?.path}"
        itemView.ivAvatar.loadImage(itemView, url)
    }

    private fun getTime(item: Event): String {

        var dateString = item.date

        val date1 = Date().time
        val date2 = dateString.toLong()


        val res = abs(date1 - date2) / 1000

        // get total days between two dates
        with(floor((res / 86400).toDouble())) {
            if (this > 0) {
                dateString = "${this.toInt()} days"
                return dateString
            }
        }

        // get hours
        val hours = floor((res / 3600).toDouble()) % 24

        val minutes = floor((res / 60).toDouble()) % 60

        return if (hours > 0) {
            "${hours.toInt()} hours ${minutes.toInt()} min"
        } else {
            "${minutes.toInt()} min"
        }
    }
}