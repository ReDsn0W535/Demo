package com.example.greenpoison.rocketbank.presenter.inter

import android.graphics.Bitmap
import com.example.greenpoison.rocketbank.view.activity.Action

interface IMainAPresenter{
    fun Draw(bitmap: Bitmap, number : Int, cur_action : Action)
    fun Fill(bitmap: Bitmap, x : Int, y : Int, number : Int, cur_action : Action)
}
