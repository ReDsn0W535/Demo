package com.example.greenpoison.rocketbank.view.inter

interface IMainAView {

    /*fun <T> request(requestFlag: Int): T?

    fun <T> response(response: T, responseFlag: Int)*/
    fun RequestView(number : Int)
    fun SetGenButtonNonCLickable()
    fun SetGenButtonCLickable()
    companion object {

        val REQUEST_ONE = 0
        val REQUEST_TWO = 1
        val REQUEST_THREE = 2

        val RESPONSE_ONE = 0
        val RESPONSE_TWO = 1
        val RESPONSE_THREE = 2
    }
}
