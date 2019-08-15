package com.example.kyrs.ui.event

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.MotionEvent
import android.view.View
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
import com.example.kyrs.ui.dialog.SuccessDialog
import com.example.kyrs.ui.plans.ProfileActivity
import com.example.kyrs.utils.setSpan
import com.example.kyrs.utils.visible
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions
import com.google.gson.Gson
import kotlinx.android.synthetic.main.activity_event.*
import kotlinx.android.synthetic.main.toolbar.*
import toothpick.Toothpick


/**
 * Project HelloMate
 * Package com.example.kyrs.ui.event
 *
 *
 *
 * Created by Timur Badretdinov (aka timurbadretdinov) 2019-08-11
 */
class EventActivity : BaseActivity(), EventView, OnMapReadyCallback {

    override var res: Int? = R.layout.activity_event

    private lateinit var mMap: GoogleMap

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

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val mapFragment = supportFragmentManager
            .findFragmentById(R.id.map) as SupportMapFragment
        mapFragment.getMapAsync(this)


        btnRegister.setOnClickListener {
            presenter.onRegisterClicked()
        }

        btnReady.visible(false)

        btnBack.setOnClickListener {
            presenter.onBackPressed()
        }

        tvOpenProfile.setSpan(
            resources.getString(R.string.open_profile),
            resources.getString(R.string.open_profile),
            R.color.black,
            true
        ) {
            presenter.onOpenProfileClicked()
        }

        //fix scrolling issue
        transparent_image.setOnTouchListener { _, event ->
            when (event.action) {
                MotionEvent.ACTION_DOWN -> {
                    // Disallow ScrollView to intercept touch events.
                    scrollView.requestDisallowInterceptTouchEvent(true)
                    // Disable touch on transparent view
                    false
                }

                MotionEvent.ACTION_UP -> {
                    // Allow ScrollView to intercept touch events.
                    scrollView.requestDisallowInterceptTouchEvent(true)
                    true
                }

                MotionEvent.ACTION_MOVE -> {
                    scrollView.requestDisallowInterceptTouchEvent(true)
                    false
                }

                else -> true
            }
        }
    }

    override fun onMapReady(googleMap: GoogleMap) {
        mMap = googleMap
        presenter.onMapReady()
    }

    override fun showEventLocation(location: LatLng) {
        mMap.addMarker(
            MarkerOptions()
                .position(location)
                .title("Location")
        )

        mMap.moveCamera(CameraUpdateFactory.newLatLng(location))
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

        if (event.participants.count() == 0) {
            tvParticipants.visibility = View.GONE
        } else {
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
    }

    override fun setButtonUnregister() {
        btnRegister.text = "Отписаться"

        btnRegister.setOnClickListener {
            presenter.onUnsubscribeClicked()
        }
    }

    override fun setButtonRegister() {
        btnRegister.text = "Учавствовать!"

        btnRegister.setOnClickListener {
            presenter.onRegisterClicked()
        }
    }

    override fun setButtonDelete() {
        btnRegister.text = "Удалить"

        btnRegister.setOnClickListener {
            presenter.onDeleteClicked()
        }
    }

    override fun openDialogSuccess() {
        val builder = SuccessDialog {
            presenter.onCloseSuccessDialog()
        }

        builder.show(supportFragmentManager, "successDialog")
    }

    override fun openProfile(userId: Int) {
        startActivity(ProfileActivity.getIntent(this, userId))
    }
}