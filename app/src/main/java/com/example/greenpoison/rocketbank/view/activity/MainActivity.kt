package com.example.greenpoison.rocketbank.view.activity

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.example.greenpoison.rocketbank.R
import com.example.greenpoison.rocketbank.presenter.impl.MainAPresenterImpl
import com.example.greenpoison.rocketbank.presenter.inter.IMainAPresenter
import com.example.greenpoison.rocketbank.view.inter.IMainAView


class MainActivity : AppCompatActivity(), IMainAView {

    var mIMainAPresenter: IMainAPresenter? = null
    override fun onCreate(savedInstanceState: Bundle?)
    {
        super.onCreate(savedInstanceState)
        mIMainAPresenter = MainAPresenterImpl(this)
        setContentView(R.layout.activity_main)
    }

    override fun <T> request(requestFlag: Int): T? {
        var ret : T? = null;
        return ret;
    }

    override fun <T> response(response: T, responseFlag: Int) {

    }
}
