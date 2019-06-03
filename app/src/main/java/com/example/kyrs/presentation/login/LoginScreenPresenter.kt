package com.example.kyrs.presentation.login

import com.arellomobile.mvp.InjectViewState
import com.example.kyrs.presentation.base.BasePresenter
import com.example.kyrs.data.network.HelloMateApi
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject


/**
 * Project Kyrs
 * Package com.example.kyrs.presenters
 *
 *
 *
 * Created by Timur Badretdinov (aka timurbadretdinov) 2019-05-31
 * Copyright © 2018 SuperEgo. All rights reserved.
 */
@InjectViewState
class LoginScreenPresenter @Inject constructor(
    var api: HelloMateApi
) : BasePresenter<LoginScreenView>() {

    override fun onFirstViewAttach() {
        super.onFirstViewAttach()
    }

    fun onButtonClicked() {

        api.getUsers()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                viewState.showMessage(it.get(0).hobbies)
            }, {
                viewState.showMessage(it.message.toString())
            }).connect()
    }
//
//    fun showMessage(message: String) {
//        viewState.showMessage(message)
//    }
}