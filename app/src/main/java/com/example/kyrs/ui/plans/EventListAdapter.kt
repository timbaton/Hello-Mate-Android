package com.example.kyrs.ui.plans

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.kyrs.R
import com.example.kyrs.data.entity.Event
import com.example.kyrs.presentation.base.BaseListAdapter
import kotlinx.android.synthetic.main.item_event.view.*

/**
 * Project HelloMate
 * Package com.example.kyrs.ui.plans
 *
 *
 *
 * Created by Timur Badretdinov (aka timurbadretdinov) 2019-08-07
 */
class EventListAdapter(private var onClick: (event: Event) -> Unit) : BaseListAdapter<Event>() {

    override fun onCreateViewHolder(parent: ViewGroup, position: Int): RecyclerView.ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_event, parent, false)
        return EventsViewHolder(view)
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        if (holder is EventsViewHolder) {
            if (items.isNotEmpty()) {
                holder.bind(items[position]!!)
                holder.itemView.root.setOnClickListener {
                    onClick(items[position]!!)
                }
            }
        }
    }
}