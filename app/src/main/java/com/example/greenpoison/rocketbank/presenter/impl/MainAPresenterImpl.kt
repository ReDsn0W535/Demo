package com.example.greenpoison.rocketbank.presenter.impl

import android.graphics.Bitmap
import android.graphics.Canvas
import com.example.greenpoison.rocketbank.model.impl.MainAModelImpl
import com.example.greenpoison.rocketbank.model.inter.IMainAModel
import com.example.greenpoison.rocketbank.presenter.inter.IMainAPresenter
import com.example.greenpoison.rocketbank.view.activity.Action
import com.example.greenpoison.rocketbank.view.inter.IMainAView

class MainAPresenterImpl(private val mIMainAView: IMainAView) : IMainAPresenter {
    override fun Fill(bitmap: Bitmap, x: Int, y: Int, cur_action : Action) {
        if (cur_action == Action.SEEDALG){
            (mIMainAModel as MainAModelImpl).FillSeed(bitmap, x, y)
        }
    }

    override fun SetDrawViewSize() {

    }

    private val mIMainAModel: IMainAModel

    init {
        mIMainAModel = MainAModelImpl()
    }

    override fun Draw(bitmap: Bitmap, number : Int, cur_action : Action)  {
        if (cur_action == Action.FIRST_DRAW) {
            (mIMainAModel as MainAModelImpl).DrawRandPoints(bitmap, number)
        } else if (cur_action == Action.RESTORE){
            (mIMainAModel as MainAModelImpl).RestorePoints(bitmap, number)
        }
    }

}
