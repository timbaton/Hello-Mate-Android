package com.example.kyrs.ui.new_event

import android.app.DatePickerDialog
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.text.format.DateUtils
import com.arellomobile.mvp.presenter.InjectPresenter
import com.arellomobile.mvp.presenter.ProvidePresenter
import com.example.kyrs.R
import com.example.kyrs.di.Scopes
import com.example.kyrs.presentation.new_event.NewEventPresenter
import com.example.kyrs.presentation.new_event.NewEventView
import com.example.kyrs.ui.base.BaseActivity
import com.example.kyrs.ui.map.MapActivity
import com.example.kyrs.utils.setSpan
import kotlinx.android.synthetic.main.activity_new_event.*
import toothpick.Toothpick
import java.util.*


/**
 * Project HelloMate
 * Package com.example.kyrs.ui.new_event
 *
 *
 *
 * Created by Timur Badretdinov (aka timurbadretdinov) 2019-08-12
 * Copyright © 2018 SuperEgo. All rights reserved.
 */
class NewEventActivity : BaseActivity(), NewEventView {

    override var res: Int? = R.layout.activity_new_event

    companion object {
        private val KEY_MAP = 945

        fun getIntent(context: Context): Intent {
            val intent = Intent(context, NewEventActivity::class.java)
            return intent
        }
    }

    @InjectPresenter
    lateinit var presenter: NewEventPresenter

    @ProvidePresenter
    fun providePresenter(): NewEventPresenter {
        return Toothpick.openScope(Scopes.Server)
            .getInstance(NewEventPresenter::class.java)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        with(llDate) {
            setOnClickListener {
                presenter.onDateClicked()
            }
        }

        tpTime.setIs24HourView(true)

        tvOpenMap.setSpan(
            resources.getString(R.string.event_open_map),
            resources.getString(R.string.event_open_map),
            R.color.text_blue_dark,
            true
        ) {
            presenter.onOpenMapClicked()
        }
    }

    override fun showDateDialog(year: Int, month: Int, date: Int, dateSetListener: DatePickerDialog.OnDateSetListener) {
        DatePickerDialog(this@NewEventActivity, dateSetListener, year, month, date)
            .show()
    }

    override fun setDate(dateAndTime: Calendar) {
        tvDate.text = DateUtils.formatDateTime(
            this,
            dateAndTime.timeInMillis,
            DateUtils.FORMAT_SHOW_DATE
        )
    }

    override fun openMapActivity() {
        startActivityForResult(MapActivity.getIntent(this), KEY_MAP)
    }
}