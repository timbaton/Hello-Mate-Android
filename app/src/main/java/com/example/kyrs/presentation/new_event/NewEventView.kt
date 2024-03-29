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
 */
interface NewEventView : BaseView {

    fun showDateDialog(year: Int, month: Int, date: Int, dateSetListener: DatePickerDialog.OnDateSetListener)

    fun showDate(dateAndTime: Calendar)
    fun showLocation(locationString: String)

    fun openDialogSuccess()

    fun openMapActivity()

    fun onBackPressed()
    fun finish()
}