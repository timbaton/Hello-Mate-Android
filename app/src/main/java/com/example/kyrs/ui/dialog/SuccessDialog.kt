package com.example.kyrs.ui.dialog

import android.R
import android.app.Dialog
import android.os.Bundle
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.DialogFragment


/**
 * Project HelloMate
 * Package com.example.kyrs.ui.dialog
 *
 *
 *
 * Created by Timur Badretdinov (aka timurbadretdinov) 2019-08-14
 */
class SuccessDialog constructor(
    private var onSuccess: () -> Unit
) : DialogFragment() {

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        // Use the Builder class for convenient dialog construction
        val builder = AlertDialog.Builder(activity!!)
        builder.setMessage(resources.getString(com.example.kyrs.R.string.success))
            .setPositiveButton(R.string.ok) { dialog, id ->
                dialog.dismiss()
                onSuccess()
            }
        return builder.create()
    }
}