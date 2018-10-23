package com.example.greenpoison.rocketbank.model.inter

import android.graphics.Bitmap

interface IMainAModel{
    fun DrawRandPoints(bitmap: Bitmap, number : Int)
    fun RestorePoints(bitmap: Bitmap, number : Int)
    fun FillSeed(bitmap: Bitmap, x: Int, y : Int, number: Int, sleepTime : Int)
    fun FillDeq(bitmap: Bitmap, x: Int, y : Int, number: Int, sleepTime : Int)
}
