package com.example.greenpoison.rocketbank.presenter.impl

import android.graphics.Bitmap
import com.example.greenpoison.rocketbank.model.impl.MainAModelImpl
import com.example.greenpoison.rocketbank.model.inter.IMainAModel
import com.example.greenpoison.rocketbank.presenter.callback.CallBack
import com.example.greenpoison.rocketbank.presenter.inter.IMainAPresenter
import com.example.greenpoison.rocketbank.view.activity.Action
import com.example.greenpoison.rocketbank.view.inter.IMainAView

class MainAPresenterImpl(private val mIMainAView: IMainAView) : IMainAPresenter, CallBack {
    override fun Fill(bitmap: Bitmap, x: Int, y: Int, cur_action : Action) {
        if (cur_action == Action.SEEDALG){
            (mIMainAModel as MainAModelImpl).FillSeed(bitmap, x, y)
        }
    }

    private val mIMainAModel: IMainAModel


    init {
        mIMainAModel = MainAModelImpl()
        mIMainAModel.registerCallback(this)
    }

    override fun Draw(bitmap: Bitmap, number : Int, cur_action : Action)  {
        if (cur_action == Action.FIRST_DRAW) {
            (mIMainAModel as MainAModelImpl).DrawRandPoints(bitmap, number)
        }
    }


    override fun RequestView() {
        mIMainAView.RequestView()
    }
}
