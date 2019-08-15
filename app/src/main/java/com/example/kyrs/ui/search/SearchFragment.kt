package com.example.kyrs.ui.search

import android.os.Bundle
import android.view.View
import com.arellomobile.mvp.presenter.InjectPresenter
import com.arellomobile.mvp.presenter.ProvidePresenter
import com.example.kyrs.R
import com.example.kyrs.data.entity.Event
import com.example.kyrs.di.Scopes
import com.example.kyrs.presentation.search.SearchPresenter
import com.example.kyrs.presentation.search.SearchView
import com.example.kyrs.ui.base.BaseFragment
import com.example.kyrs.ui.event.EventActivity
import com.example.kyrs.ui.plans.EventListAdapter
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
 */
class SearchFragment : BaseFragment(), SearchView {

    override val layoutRes: Int = R.layout.fragment_search

    lateinit var adapter: EventListAdapter

    @InjectPresenter
    lateinit var presenter: SearchPresenter

    @ProvidePresenter
    fun providePresenter(): SearchPresenter {
        return Toothpick.openScope(Scopes.Server)
            .getProvider(SearchPresenter::class.java).get()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initList()

        swipeRefresh.setOnRefreshListener {
            presenter.onRefreshCalled()
        }
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
        } else {
            tvState.text = "list is empty"
        }
    }

    override fun updateEvents(events: List<Event>?) {
        if (events?.isNotEmpty()!!) {
            adapter.updateList(events)
        } else {
            tvState.text = "list is empty"
        }
    }

    override fun hideLoader() {
        swipeRefresh.isRefreshing = false
    }

    override fun showProgressBar() {
        progressBar.visibility = View.VISIBLE
    }

    override fun hideProgressBar() {
        progressBar.visibility = View.GONE
    }

    override fun openEventActivity(event: Event) {
        val eventGson = Gson().toJson(event)
        startActivity(EventActivity.getIntent(context!!, eventGson))
    }
}