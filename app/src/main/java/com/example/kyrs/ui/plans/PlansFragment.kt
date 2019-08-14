package com.example.kyrs.ui.plans

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import android.view.Menu
import android.view.MenuInflater
import android.view.View
import com.arellomobile.mvp.presenter.InjectPresenter
import com.arellomobile.mvp.presenter.ProvidePresenter
import com.example.kyrs.R
import com.example.kyrs.data.entity.Event
import com.example.kyrs.di.Scopes
import com.example.kyrs.presentation.plans.PlansPresenter
import com.example.kyrs.presentation.plans.PlansView
import com.example.kyrs.ui.base.BaseFragment
import com.example.kyrs.ui.event.EventActivity
import com.example.kyrs.ui.new_event.NewEventActivity
import com.example.kyrs.utils.visible
import com.google.gson.Gson
import kotlinx.android.synthetic.main.fragment_plans.*
import toothpick.Toothpick

/**
 * Project HelloMate
 * Package com.example.kyrs.ui.plans
 *
 *
 *
 * Created by Timur Badretdinov (aka timurbadretdinov) 2019-07-10
 * Copyright © 2018 SuperEgo. All rights reserved.
 */
class PlansFragment : BaseFragment(), PlansView {

    override val layoutRes: Int = R.layout.fragment_plans

    lateinit var adapter: EventListAdapter

    @InjectPresenter
    lateinit var presenter: PlansPresenter

    @ProvidePresenter
    fun providePresenter(): PlansPresenter {
        return Toothpick.openScope(Scopes.Server).getInstance(PlansPresenter::class.java)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        (activity as AppCompatActivity).setSupportActionBar(toolbar)

        initList()

        swipeRefresh.setOnRefreshListener {
            presenter.onRefreshCalled()
        }
    }

    override fun onCreateOptionsMenu(menu: Menu?, inflater: MenuInflater?) {
        inflater?.inflate(R.menu.menu_new_event, menu)

        for (i in 0 until menu!!.size()) {
            val item = menu.getItem(i)
            if (item.itemId == R.id.menu_add) {
                val button = item.actionView
                if (button != null) {
                    button.setOnClickListener {
                        presenter.onAddButtonClicked()
                    }
                }
            }
        }

        super.onCreateOptionsMenu(menu, inflater)
    }

    private fun initList() {
        adapter = EventListAdapter { event ->
            presenter.onEventClicked(event)
        }

        rvList.setHasFixedSize(true)
        rvList.layoutManager = androidx.recyclerview.widget.LinearLayoutManager(context)
        rvList.adapter = adapter

    }

    override fun showEvents(events: List<Event>?) {
        if (events?.isNotEmpty()!!) {
            adapter.addList(events)
            tvState.visible(false)
        } else {
            tvState.visible(true)
            tvState.text = "list is empty"
        }
    }

    override fun updateEvents(events: List<Event>?) {
        if (events?.isNotEmpty()!!) {
            adapter.updateList(events)
            tvState.visible(false)
        } else {
            tvState.visible(true)
            tvState.text = "list is empty"
        }
    }

    override fun hideLoader() {
        swipeRefresh.isRefreshing = false
    }

    override fun openEventActivity(event: Event) {
        val eventGson = Gson().toJson(event)
        startActivity(EventActivity.getIntent(context!!, eventGson))
    }

    override fun openNewEventActivity() {
        startActivity(NewEventActivity.getIntent(context!!))
    }
}