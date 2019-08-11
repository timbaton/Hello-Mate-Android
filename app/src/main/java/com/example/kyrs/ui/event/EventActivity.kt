package com.example.kyrs.ui.event

import android.content.Context
import android.content.Intent
import android.view.ViewGroup
import android.widget.ImageView
import com.arellomobile.mvp.presenter.InjectPresenter
import com.arellomobile.mvp.presenter.ProvidePresenter
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.CircleCrop
import com.example.kyrs.R
import com.example.kyrs.data.entity.Event
import com.example.kyrs.di.Scopes
import com.example.kyrs.presentation.event.EventPresenter
import com.example.kyrs.presentation.event.EventView
import com.example.kyrs.ui.base.BaseActivity
import com.google.gson.Gson
import kotlinx.android.synthetic.main.activity_event.*
import toothpick.Toothpick
import android.os.Bundle
import com.example.kyrs.ui.plans.ProfileActivity
import com.example.kyrs.ui.plans.ProfileFragment


/**
 * Project HelloMate
 * Package com.example.kyrs.ui.event
 *
 *
 *
 * Created by Timur Badretdinov (aka timurbadretdinov) 2019-08-11
 * Copyright © 2018 SuperEgo. All rights reserved.
 */
class EventActivity : BaseActivity(), EventView {

    override var res: Int? = R.layout.activity_event

    companion object {
        private var KEY_EVENT = "key_event"

        fun getIntent(context: Context, event: String): Intent {
            val intent = Intent(context, EventActivity::class.java)
            intent.putExtra(KEY_EVENT, event)
            return intent
        }
    }

    @InjectPresenter
    lateinit var presenter: EventPresenter

    @ProvidePresenter
    fun providePresenter(): EventPresenter {
        val event = Gson().fromJson(intent.getStringExtra(KEY_EVENT), Event::class.java)

        return Toothpick.openScope(Scopes.Server)
            .getInstance(EventPresenter::class.java).apply {
                this.event = event
            }
    }

    override fun showEvent(event: Event, imagePath: String) {
        val name = event.owner.name + " " + event.owner.surname
        tvName.text = name

        val url = "$imagePath${event.owner.avatar.path}"
        Glide.with(this)
            .load(url)
            .transform(CircleCrop())
            .into(ivAvatar)

        tvDescription.text = event.description


        val scale = baseContext.resources.displayMetrics.density;

        event.participants.forEach { user ->
            val imageView = with(ImageView(this)) {

                layoutParams = ViewGroup.MarginLayoutParams(
                    ViewGroup.LayoutParams.MATCH_PARENT,
                    ViewGroup.LayoutParams.WRAP_CONTENT
                ).also {
                    it.marginStart = (12 * scale + 0.5f).toInt()
                    it.topMargin = (4 * scale + 0.5f).toInt()
                    it.width = (50 * scale + 0.5f).toInt()
                    it.height = (50 * scale + 0.5f).toInt()
                }

                val urlParticipant = "$imagePath${user.avatar.path}"
                Glide.with(this@EventActivity)
                    .load(urlParticipant)
                    .transform(CircleCrop())
                    .into(this)
            }

            imageView.view.setOnClickListener { presenter.onParticipantClicked(user.id) }
            listParticipants.addView(imageView.view)
        }
    }

    override fun openProfile(userId: Int) {
       startActivity(ProfileActivity.getIntent(this, userId))
    }
}