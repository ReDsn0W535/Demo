package com.example.greenpoison.rocketbank.presenter.callback

import android.os.AsyncTask

interface CallBack {
    fun RequestView(number: Int)
    fun SetGenButtonNonCLickable()
    fun SetGenButtonCLickable()
    fun GetTaskStatus(status: String, number : Int)
}
