package com.example.greenpoison.rocketbank.presenter.impl

import android.graphics.Bitmap
import android.graphics.Point
import com.example.greenpoison.rocketbank.model.impl.MainAModelImpl
import com.example.greenpoison.rocketbank.model.inter.IMainAModel
import com.example.greenpoison.rocketbank.presenter.inter.IMainAPresenter
import com.example.greenpoison.rocketbank.view.inter.IMainAView

class MainAPresenterImpl(private val mIMainAView: IMainAView) : IMainAPresenter {

    private val mIMainAModel: IMainAModel

    init {
        mIMainAModel = MainAModelImpl()
    }

    override fun GetPoints(bitmap: Bitmap)  {
        (mIMainAModel as MainAModelImpl).DrawRandPoints(bitmap)
    }
}
