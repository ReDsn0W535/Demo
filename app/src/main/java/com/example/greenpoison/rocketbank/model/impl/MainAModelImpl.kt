package com.example.greenpoison.rocketbank.model.impl

import android.graphics.Bitmap
import android.graphics.Color
import android.graphics.Point
import com.example.greenpoison.rocketbank.model.inter.IMainAModel
import java.util.*
import kotlin.collections.ArrayList
import kotlin.collections.RandomAccess
import kotlin.math.*

class MainAModelImpl : IMainAModel{
    override fun DrawRandPoints(bitmap: Bitmap) {
        val r = Random()
            for (i in 0..bitmap.width-1)
                for (j in 0..bitmap.height-1){
                    var rand = (0..100).random()
                    if (rand*100 > 97){
                        var p = Point(i, j)
                        bitmap.setPixel(i,j,Color.BLACK)
                    }
                }
    }

    fun IntRange.random() =
            Random().nextDouble() +  start

}
