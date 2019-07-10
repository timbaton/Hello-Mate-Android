package com.example.kyrs.ui.base

import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import com.arellomobile.mvp.MvpAppCompatFragment
import com.example.kyrs.presentation.base.BaseView
import com.example.kyrs.utils.showToast

/**
 * Project HelloMate
 * Package com.example.kyrs.ui.base
 *
 *
 *
 * Created by Timur Badretdinov (aka timurbadretdinov) 2019-07-10
 * Copyright © 2018 SuperEgo. All rights reserved.
 */
abstract class BaseFragment : MvpAppCompatFragment(), BaseView {

    abstract val layoutRes: Int

    override fun showMessage(message: String) {
        context?.showToast(message)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?)
            = inflater.inflate(layoutRes, container, false)!!
}