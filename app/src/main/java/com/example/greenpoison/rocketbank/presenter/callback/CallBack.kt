package com.example.greenpoison.rocketbank.presenter.callback

interface CallBack<T> {
    fun onSuccess(response: T)

    fun onError(t: Throwable)
}
