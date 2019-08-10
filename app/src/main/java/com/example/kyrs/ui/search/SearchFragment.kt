package com.example.kyrs.ui.plans

import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.arellomobile.mvp.presenter.InjectPresenter
import com.arellomobile.mvp.presenter.ProvidePresenter
import com.example.kyrs.R
import com.example.kyrs.data.entity.Event
import com.example.kyrs.di.Scopes
import com.example.kyrs.presentation.search.SearchPresenter
import com.example.kyrs.presentation.search.SearchView
import com.example.kyrs.ui.base.BaseFragment
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
class SearchFragment : BaseFragment(), SearchView {

    override val layoutRes: Int = R.layout.fragment_search

    @InjectPresenter
    lateinit var presenter: SearchPresenter

    @ProvidePresenter
    fun providePresenter(): SearchPresenter {
        return Toothpick.openScope(Scopes.Server)
            .getProvider(SearchPresenter::class.java).get()
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {

        return super.onCreateView(inflater, container, savedInstanceState)
    }

    override fun showEvents(events: List<Event>) {
        val adapter = EventListAdapter()
        rvList.adapter = adapter
        rvList.layoutManager = LinearLayoutManager(context)

        if (events.isNotEmpty()) {
            adapter.addList(events)
        } else {
            showMessage("list is empty")
        }
    }
}