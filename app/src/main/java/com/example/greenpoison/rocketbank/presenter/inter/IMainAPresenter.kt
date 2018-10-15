package com.example.greenpoison.rocketbank.presenter.inter

import android.graphics.Bitmap
import android.graphics.Canvas
import com.example.greenpoison.rocketbank.view.activity.Action

interface IMainAPresenter{
    fun Draw(bitmap: Bitmap, number : Int, cur_action : Action)
    fun SetDrawViewSize()
    fun Fill(bitmap: Bitmap, x : Int, y : Int, cur_action : Action)
}
