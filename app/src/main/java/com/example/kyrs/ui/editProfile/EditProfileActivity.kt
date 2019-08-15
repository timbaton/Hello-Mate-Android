package com.example.kyrs.ui.editProfile

import android.content.Context
import android.content.Intent
import com.arellomobile.mvp.presenter.InjectPresenter
import com.arellomobile.mvp.presenter.ProvidePresenter
import com.example.kyrs.R
import com.example.kyrs.di.Scopes
import com.example.kyrs.presentation.edit_profile.EditProfilePresenter
import com.example.kyrs.presentation.edit_profile.EditProfileView
import com.example.kyrs.ui.base.BaseActivity
import toothpick.Toothpick

/**
 * Project HelloMate
 * Package com.example.kyrs.ui.EditProfile
 *
 *
 *
 * Created by Timur Badretdinov (aka timurbadretdinov) 2019-08-14
 */
class EditProfileActivity : BaseActivity(), EditProfileView {

    override var res: Int? = R.layout.activity_edit_profile

    companion object {
        fun getIntent(context: Context): Intent {
            return Intent(context, EditProfileActivity::class.java)

        }
    }

    @InjectPresenter
    lateinit var presenter: EditProfilePresenter

    @ProvidePresenter
    fun providePresenter(): EditProfilePresenter {
        return Toothpick.openScope(Scopes.Server)
            .getInstance(EditProfilePresenter::class.java)
    }
}