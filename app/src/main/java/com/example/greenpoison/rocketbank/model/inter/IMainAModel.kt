package com.example.greenpoison.rocketbank.model.inter

import android.graphics.Bitmap
import android.graphics.Canvas
import android.graphics.Point

interface IMainAModel{
    fun DrawRandPoints(bitmap: Bitmap, number : Int)
    fun RestorePoints(bitmap: Bitmap, number : Int)
    fun FillSeed(bitmap: Bitmap, x: Int, y : Int)
}
