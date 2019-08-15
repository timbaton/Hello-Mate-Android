package com.example.kyrs.presentation.base

import android.view.View

/**
 * Project HelloMate
 * Package com.example.kyrs.presentation.base
 *
 *
 *
 * Created by Timur Badretdinov (aka timurbadretdinov) 2019-08-07
 */
abstract class BaseListViewHolder<T>(itemView: View) : androidx.recyclerview.widget.RecyclerView.ViewHolder(itemView) {

    abstract fun bind(item: T)

}