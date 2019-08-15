package com.example.kyrs.ui.plans

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.View
import com.arellomobile.mvp.presenter.InjectPresenter
import com.arellomobile.mvp.presenter.ProvidePresenter
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.CircleCrop
import com.example.kyrs.R
import com.example.kyrs.data.entity.response.ProfileResponse
import com.example.kyrs.di.Scopes
import com.example.kyrs.presentation.profile.ProfilePresenter
import com.example.kyrs.presentation.profile.ProfileView
import com.example.kyrs.ui.base.BaseActivity
import com.example.kyrs.ui.login.LoginScreenActivity
import com.example.kyrs.utils.visible
import kotlinx.android.synthetic.main.fragment_profile.*
import kotlinx.android.synthetic.main.toolbar.*
import toothpick.Toothpick

/**
 * Project HelloMate
 * Package com.example.kyrs.ui.plans
 *
 *
 *
 * Created by Timur Badretdinov (aka timurbadretdinov) 2019-07-10
 */
class ProfileActivity : BaseActivity(), ProfileView {

    override var res: Int? = R.layout.fragment_profile

    companion object {
        private var USER_ID = "userId"

        fun getIntent(context: Context, userId: Int): Intent {
            val intent = Intent(context, ProfileActivity::class.java)
            intent.putExtra(USER_ID, userId)
            return intent
        }
    }

    @InjectPresenter
    lateinit var presenter: ProfilePresenter

    @ProvidePresenter
    fun providePresenter(): ProfilePresenter {
        val id = intent.getIntExtra(USER_ID, 0)

        return Toothpick.openScope(Scopes.Server)
            .getInstance(ProfilePresenter::class.java).apply {
                userId = id
            }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        llProfileSettings.visibility = View.GONE
        tvExit.setOnClickListener {
            presenter.onExitClicked()
        }

        btnBack.setOnClickListener {
            presenter.onBackPressed()
        }
        btnReady.visible(false)
    }

    override fun fillProfileData(data: ProfileResponse?, serverPath: String) {
        tvName.text = data?.name + " " + data?.surname
        tvMail.text = data?.mail
        tvPhone.text = data?.phone

        val url = if (data?.avatar != null) "$serverPath/uploads/${data.avatar.path}" else null
        Glide.with(this)
            .load(url)
            .transform(CircleCrop())
            .into(ivAvatar)
    }

    override fun openLoginActivity() {
        startActivity(LoginScreenActivity.getIntent(this))
        finish()
    }
}