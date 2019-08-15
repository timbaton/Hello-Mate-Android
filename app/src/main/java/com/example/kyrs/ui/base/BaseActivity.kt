package com.example.kyrs.ui.base

import android.os.Bundle
import android.widget.Toast
import com.example.kyrs.presentation.base.BaseView

/**
 * Project Kyrs
 * Package com.example.kyrs.base
 *
 *
 *
 * Created by Timur Badretdinov (aka timurbadretdinov) 2019-05-31
 */
abstract class BaseActivity : com.example.kyrs.migratedMoxy.MvpAppCompatActivity(), BaseView {

    abstract var res: Int?

    override fun showMessage(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        res?.let {
            setContentView(it)
        }
    }
}