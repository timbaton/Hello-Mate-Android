package com.example.kyrs.presentation.new_event

import android.app.DatePickerDialog
import com.arellomobile.mvp.InjectViewState
import com.example.kyrs.presentation.base.BasePresenter
import java.util.*
import javax.inject.Inject

/**
 * Project HelloMate
 * Package com.example.kyrs.presentation.new_event
 *
 *
 *
 * Created by Timur Badretdinov (aka timurbadretdinov) 2019-08-12
 * Copyright © 2018 SuperEgo. All rights reserved.
 */
@InjectViewState
class NewEventPresenter @Inject constructor(

) : BasePresenter<NewEventView>() {

    var dateAndTime = Calendar.getInstance()

    override fun onFirstViewAttach() {
        super.onFirstViewAttach()
        viewState.setDate(dateAndTime)
    }

    // установка обработчика выбора даты
    var dateSetListener = DatePickerDialog.OnDateSetListener { _, year, month, dayOfMonth ->
        dateAndTime.set(Calendar.YEAR, year)
        dateAndTime.set(Calendar.MONTH, month)
        dateAndTime.set(Calendar.DAY_OF_MONTH, dayOfMonth)
        viewState.setDate(dateAndTime)
    }

    fun onDateClicked() {
        val year = dateAndTime.get(Calendar.YEAR)
        val month = dateAndTime.get(Calendar.MONTH)
        val date = dateAndTime.get(Calendar.DAY_OF_MONTH)
        viewState.showDateDialog(year, month, date, dateSetListener)
    }
}