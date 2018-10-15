package com.example.greenpoison.rocketbank.view.fragment

import android.app.Activity
import android.content.DialogInterface
import android.support.v7.app.AlertDialog
import android.widget.Toast
import com.example.greenpoison.rocketbank.R

class DialogScreen {
    fun getDialog(activity: Activity): AlertDialog {


        val dialogClickListener = DialogInterface.OnClickListener { dialog, which ->
            when (which) {
                DialogInterface.BUTTON_POSITIVE -> {
                }
                DialogInterface.BUTTON_NEGATIVE -> {
                }
            }
        }


        val builder = AlertDialog.Builder(activity)
        var inflater = activity.layoutInflater

        builder.setTitle("Введите размер!")
                .setCancelable(false)
                .setNegativeButton("Cancel", dialogClickListener)
                .setPositiveButton("OK", dialogClickListener)
                .setView(inflater.inflate(R.layout.sizechoosinglayout, null))
        return builder.create()
    }
}