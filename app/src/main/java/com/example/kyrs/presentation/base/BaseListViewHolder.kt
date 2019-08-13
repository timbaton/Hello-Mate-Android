package com.example.kyrs.presentation.base

import androidx.recyclerview.widget.RecyclerView
import android.view.View

/**
 * Project HelloMate
 * Package com.example.kyrs.presentation.base
 *
 *
 *
 * Created by Timur Badretdinov (aka timurbadretdinov) 2019-08-07
 * Copyright © 2018 SuperEgo. All rights reserved.
 */
abstract class BaseListViewHolder<T>(itemView: View): androidx.recyclerview.widget.RecyclerView.ViewHolder(itemView) {

    abstract fun bind(item: T)

}