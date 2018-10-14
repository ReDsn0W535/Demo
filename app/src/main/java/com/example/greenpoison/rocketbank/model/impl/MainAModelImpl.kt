package com.example.greenpoison.rocketbank.model.impl

import android.graphics.*
import android.util.Log
import com.example.greenpoison.rocketbank.model.inter.IMainAModel
import java.util.*
import kotlin.collections.ArrayList
import kotlin.collections.RandomAccess
import kotlin.math.*

class MainAModelImpl : IMainAModel{

   // var IS_CALLED_FIRST_TIME = false
    var points = ArrayList<Point>(0)

    override fun DrawRandPoints(canvas: Canvas, DRAW_PERMISSION : Boolean) {
        var p = Paint()
        p.setColor(Color.BLACK)
        p.setStrokeWidth(30F)

        //if (IS_CALLED_FIRST_TIME == false) {
            var i = 0
            var j = 0
            while (i < canvas?.width - 1) {
                while (j < canvas?.height - 1) {
                    var rand = (0..100).random()
                    if (rand * 100 > 80) {
                        canvas?.drawPoint(i.toFloat(), j.toFloat(), p)
                        points.add(Point(i,j))
                    }
                    j += 30
                }
                j = 0
                i += 30
            }
        //    IS_CALLED_FIRST_TIME = true
       // } else{
          //  for (i in points){
          //      canvas!!.drawPoint(i.x.toFloat(),i.y.toFloat(), p)
          //  }
        //}
    }

    fun IntRange.random() =
            Random().nextDouble() +  start

}
