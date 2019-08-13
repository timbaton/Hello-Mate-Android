package com.example.kyrs.presentation.new_event

import android.app.DatePickerDialog
import com.example.kyrs.presentation.base.BaseView
import java.util.*

/**
 * Project HelloMate
 * Package com.example.kyrs.presentation.new_event
 *
 *
 *
 * Created by Timur Badretdinov (aka timurbadretdinov) 2019-08-12
 * Copyright © 2018 SuperEgo. All rights reserved.
 */
interface NewEventView : BaseView {

    fun showDateDialog(year: Int, month: Int, date: Int, dateSetListener: DatePickerDialog.OnDateSetListener)

    fun setDate(dateAndTime: Calendar)
}