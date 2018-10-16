package com.example.greenpoison.rocketbank.presenter.impl

import android.graphics.Bitmap
import com.example.greenpoison.rocketbank.model.impl.MainAModelImpl
import com.example.greenpoison.rocketbank.model.inter.IMainAModel
import com.example.greenpoison.rocketbank.presenter.callback.CallBack
import com.example.greenpoison.rocketbank.presenter.inter.IMainAPresenter
import com.example.greenpoison.rocketbank.view.activity.Action
import com.example.greenpoison.rocketbank.view.inter.IMainAView
import kotlin.concurrent.thread

class MainAPresenterImpl(private val mIMainAView: IMainAView) : IMainAPresenter, CallBack {
    override fun SetGenButtonNonCLickable() {
        mIMainAView.SetGenButtonNonCLickable()
    }

    override fun SetGenButtonCLickable() {
        mIMainAView.SetGenButtonCLickable()
    }
    override fun Fill(bitmap: Bitmap, x: Int, y: Int, number: Int, cur_action : Action) {
        if (cur_action == Action.SEEDALG){
            if (number == 1)thread(true, name = "FillSeedThread1") { mIMainAModel1.FillSeed(bitmap, x, y, number)}
            if (number == 2)thread(true, name = "FillSeedThread2") { mIMainAModel2.FillSeed(bitmap, x, y, number)}
        } else if (cur_action == Action.XORALG){
            if (number == 1)thread(true, name = "XORThread1") { mIMainAModel1.FillXOR(bitmap, x, y, number)}
            if (number == 2)thread(true, name = "XORThread2") { mIMainAModel2.FillXOR(bitmap, x, y, number)}
        }
    }

    private val mIMainAModel1: IMainAModel
    private val mIMainAModel2: IMainAModel

    init {
        mIMainAModel1 = MainAModelImpl()
        mIMainAModel1.registerCallback(this)
        mIMainAModel2 = MainAModelImpl()
        mIMainAModel2.registerCallback(this)
    }

    override fun Draw(bitmap: Bitmap, number : Int, cur_action : Action)  {
        if (cur_action == Action.FIRST_DRAW && number == 1) {

            mIMainAModel1.DrawRandPoints(bitmap, number)
        }
        if (cur_action == Action.FIRST_DRAW && number == 2) {
            mIMainAModel2.DrawRandPoints(bitmap, number)
        }
    }


    override fun RequestView(number: Int) {
        mIMainAView.RequestView(number)
    }
}
