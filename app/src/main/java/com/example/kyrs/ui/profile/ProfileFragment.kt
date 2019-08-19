package com.example.kyrs.ui.profile

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
import com.example.kyrs.ui.base.BaseFragment
import com.example.kyrs.ui.edit_profile.EditProfileActivity
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
class ProfileFragment : BaseFragment(), ProfileView {

    override val layoutRes: Int = R.layout.fragment_profile

    companion object {
        val REQUEST_EDIT_PROFILE = 133
    }

    @InjectPresenter
    lateinit var presenter: ProfilePresenter

    @ProvidePresenter
    fun providePresenter(): ProfilePresenter {
        return Toothpick.openScope(Scopes.Server)
            .getInstance(ProfilePresenter::class.java)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        tvExit.setOnClickListener {
            presenter.onExitClicked()
        }

        tvEdit.setOnClickListener {
            presenter.onEditClicked()
        }
        toolbar.visible(false)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        presenter.onActivityResult(requestCode, resultCode)
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

    override fun hideUserContacts() {
        llContacts.visible(false)
    }

    override fun showUserContacts() {
        llContacts.visible(true)
    }

    override fun openLoginActivity() {
        startActivity(LoginScreenActivity.getIntent(context!!))
        activity?.finish()
    }

    override fun onBackPressed() {
        activity?.onBackPressed()
    }

    override fun openEditProfileActivity() {
        startActivityForResult(EditProfileActivity.getIntent(context!!), REQUEST_EDIT_PROFILE)
    }
}