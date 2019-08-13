package com.example.kyrs.presentation.base

import androidx.recyclerview.widget.RecyclerView

/**
 * Project HelloMate
 * Package com.example.kyrs.presentation.base
 *
 *
 *
 * Created by Timur Badretdinov (aka timurbadretdinov) 2019-08-07
 * Copyright © 2018 SuperEgo. All rights reserved.
 */
abstract class BaseListAdapter<T> : androidx.recyclerview.widget.RecyclerView.Adapter<androidx.recyclerview.widget.RecyclerView.ViewHolder>() {

    protected val items: MutableList<T?> = mutableListOf()

    override fun getItemCount(): Int = items.size

    fun addItem(item: T) {
        items.add(item)
        notifyItemInserted(items.size - 1)
    }

    fun addList(list: List<T>) {

        val start = items.size
        items.addAll(list)

        val end = items.size

        notifyItemRangeInserted(start, end)
    }

    fun updateList(list: List<T>) {
        items.clear()
        items.addAll(list)

        notifyDataSetChanged()
    }

    fun changeItem(item: T, index: Int) {
        items[index] = item
        notifyItemChanged(index)
    }

    fun removeItem(index: Int) {
        items.removeAt(index)
        notifyItemRemoved(index)
    }

    fun clearItems() {
        items.clear()
        notifyDataSetChanged()
    }
}