package com.example.greenpoison.rocketbank.presenter.callback

import android.app.DownloadManager
import javax.security.auth.callback.Callback

interface CallBack {
    fun RequestView(number: Int)
    fun SetGenButtonNonCLickable()
    fun SetGenButtonCLickable()
}
