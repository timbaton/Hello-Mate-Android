package com.example.kyrs.presentation.base

import com.arellomobile.mvp.MvpPresenter
import com.arellomobile.mvp.MvpView
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable

/**
 * Project Kyrs
 * Package com.example.kyrs.base
 *
 *
 *
 * Created by Timur Badretdinov (aka timurbadretdinov) 2019-05-31
 * Copyright © 2018 SuperEgo. All rights reserved.
 */
abstract class BasePresenter<view: MvpView> : MvpPresenter<view>() {

    private val compositeDisposable = CompositeDisposable()

    override fun onDestroy() {
        compositeDisposable.dispose()
        super.onDestroy()
    }

    protected fun Disposable.connect() {
        compositeDisposable.add(this)
    }

    open fun test() {

    }
}