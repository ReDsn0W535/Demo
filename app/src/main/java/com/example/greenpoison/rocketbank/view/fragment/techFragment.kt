package com.example.greenpoison.rocketbank.view.fragment

import android.app.Dialog
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import com.example.greenpoison.rocketbank.R
import android.content.DialogInterface
import android.support.v7.app.AlertDialog
import android.widget.EditText
import android.support.annotation.NonNull
import android.support.v4.app.DialogFragment
import android.support.v4.app.FragmentTransaction


class TechFragment : Fragment(), View.OnClickListener {
    override fun onClick(v: View?) {
        var custDialog = CustomDialogFragment()
        var fr = fragmentManager!!.beginTransaction()
        custDialog.show(fr, "dialog")
    }


    class CustomDialogFragment : DialogFragment() {

        override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {

            var builder = AlertDialog.Builder(activity!!)
            return builder.setTitle("Диалоговое окно").setMessage("Для закрытия окна нажмите ОК").setPositiveButton(android.R.string.yes, DialogInterface.OnClickListener(){ dialogInterface: DialogInterface, i: Int -> i

            }).setNegativeButton(android.R.string.no, DialogInterface.OnClickListener(){ dialogInterface: DialogInterface, i: Int -> i

            }).setView(R.layout.sizechoosinglayout).create()
        }
    }



    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        var v = inflater.inflate(R.layout.techfragment, null)
        var sizeButton = v.findViewById<Button>(R.id.sizebutton)
        sizeButton.setOnClickListener(this)
        return v
    }
}