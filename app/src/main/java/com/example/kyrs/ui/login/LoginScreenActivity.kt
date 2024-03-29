package com.example.kyrs.ui.login

import android.content.Context
import android.content.Intent
import android.os.Bundle
import com.arellomobile.mvp.presenter.InjectPresenter
import com.arellomobile.mvp.presenter.ProvidePresenter
import com.example.kyrs.R
import com.example.kyrs.di.Scopes
import com.example.kyrs.presentation.login.LoginScreenPresenter
import com.example.kyrs.presentation.login.LoginScreenView
import com.example.kyrs.ui.base.BaseActivity
import com.example.kyrs.ui.main.MainContainerActivity
import com.example.kyrs.ui.registration.RegistrationActivity
import kotlinx.android.synthetic.main.activity_login.*
import toothpick.Toothpick

/**
 * Project Kyrs
 * Package com.example.kyrs.ui
 *
 *
 *
 * Created by Timur Badretdinov (aka timurbadretdinov) 2019-05-31
 */
class LoginScreenActivity : BaseActivity(), LoginScreenView {

    override var res: Int? = R.layout.activity_login

    companion object {
        fun getIntent(screen: Context): Intent {
            return Intent(screen, LoginScreenActivity::class.java)
        }
    }

    @InjectPresenter
    lateinit var presenter: LoginScreenPresenter

    @ProvidePresenter
    fun providePresenter(): LoginScreenPresenter {
        return Toothpick.openScope(Scopes.Server)
            .getInstance(LoginScreenPresenter::class.java)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        btnEnter.setOnClickListener { presenter.onLoginClicked(etLogin.text.toString(), etPassword.text.toString()) }

        btnNewUser.setOnClickListener { presenter.onRegisterClicked() }
    }

    override fun openRegistrationActivity() {
        val intent = RegistrationActivity.getIntent(this)
        startActivity(intent)
    }

    override fun openMainScreen() {
        val intent = MainContainerActivity.getIntent(this)
        startActivity(intent)
        finish()
    }
}