package com.example.kyrs.presentation.base

import com.arellomobile.mvp.MvpView

/**
 * Project Kyrs
 * Package com.example.kyrs.base
 *
 *
 *
 * Created by Timur Badretdinov (aka timurbadretdinov) 2019-05-31
 */
interface BaseView : MvpView {

    fun showMessage(message: String);

}