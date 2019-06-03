package com.example.kyrs.presentation.base

import com.arellomobile.mvp.MvpView

/**
 * Project Kyrs
 * Package com.example.kyrs.base
 *
 *
 *
 * Created by Timur Badretdinov (aka timurbadretdinov) 2019-05-31
 * Copyright © 2018 SuperEgo. All rights reserved.
 */
interface BaseView : MvpView {

    fun showMessage(message: String);

}