package com.example.greenpoison.rocketbank.view.inter

import android.os.AsyncTask

interface IMainAView {
    fun RequestView(number : Int)
    fun SetGenButtonNonCLickable()
    fun SetGenButtonCLickable()
    fun GetTaskStatus(status: String, number : Int)
    companion object {

        val REQUEST_ONE = 0
        val REQUEST_TWO = 1
        val REQUEST_THREE = 2

        val RESPONSE_ONE = 0
        val RESPONSE_TWO = 1
        val RESPONSE_THREE = 2
    }
}
