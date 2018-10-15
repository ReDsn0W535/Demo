package com.example.greenpoison.rocketbank.model.impl

import android.graphics.*
import android.util.Log
import android.widget.Toast
import com.example.greenpoison.rocketbank.model.inter.IMainAModel
import java.util.*
import kotlin.collections.ArrayList
import kotlin.collections.RandomAccess
import kotlin.math.*

class MainAModelImpl : IMainAModel {
    // var IS_CALLED_FIRST_TIME = false
    var points1 = ArrayList<Point>(0)
    var points2 = ArrayList<Point>(0)

    var p = Paint()
    var PointsStack = Stack<Point>()
    override fun FillSeed(bitmap: Bitmap, x: Int, y: Int) {
        if (bitmap.getPixel(x, y) == Color.WHITE) {
            Log.e("FILLSEED_TAG", "НЕЛЬЗЯ НАЖИМАТЬ НА БЕЛУЮ ОБЛАСТЬ")
            return
        }
        PointsStack.push(Point(x, y))
        FillPoints(bitmap)

    }


    fun FillPoints(bitmap: Bitmap) {
        if (PointsStack.size == 0) return
        var cur_point = PointsStack.pop()
        Log.e("FILL POINTS TAG", "x = ${cur_point.x}, y = ${cur_point.y}, color = ${bitmap.getPixel(cur_point.x, cur_point.y)}")
        Log.e("FILL POINTS TAG", "Black = ${Color.BLACK}, CUR_COLOR = ${bitmap.getPixel(cur_point.x, cur_point.y)}")
        bitmap.setPixel(cur_point.x, cur_point.y, Color.RED)
        if (cur_point.x != bitmap.width - 1) {
            if (bitmap.getPixel(cur_point.x + 1, cur_point.y) == (Color.BLACK)) {
                PointsStack.push(Point(cur_point.x + 1, cur_point.y))
            }
        }

        if (cur_point.x != 0) {
            if (bitmap.getPixel(cur_point.x - 1, cur_point.y) == Color.BLACK) {
                PointsStack.push(Point(cur_point.x - 1, cur_point.y))
            }
        }

        if (cur_point.y != bitmap.height - 1) {
            if (bitmap.getPixel(cur_point.x, cur_point.y + 1) == Color.BLACK) {
                PointsStack.push(Point(cur_point.x, cur_point.y + 1))
            }
        }

        if (cur_point.y != 0) {
            if (bitmap.getPixel(cur_point.x, cur_point.y - 1) == Color.BLACK) {
                PointsStack.push(Point(cur_point.x, cur_point.y - 1))
            }
        }
        FillPoints(bitmap)
    }

    override fun DrawRandPoints(bitmap: Bitmap, number: Int) {
        var points = ArrayList<Point>(0)

        //if (IS_CALLED_FIRST_TIME == false) {
        var i = 0
        var j = 0
        while (i < bitmap.width - 1) {
            while (j < bitmap.height - 1) {
                var rand = (0..100).random()
                if (rand * 100 > 70) {
                    for (x in i..i + 30) {
                        for (y in j..j + 30) {
                            bitmap.setPixel(x, y, Color.BLACK)
                            if (y == bitmap.height - 1) break
                        }
                        if (x == bitmap.width - 1) break
                    }
                    points.add(Point(i, j))
                }
                j += 30
            }
            j = 0
            i += 30
        }
        if (number == 1) points1 = points
        else points2 = points
    }

    override fun RestorePoints(bitmap: Bitmap, number: Int) {
        if (number == 1) {
            for (i in points1) {
                for (x in i.x..i.x + 30) {
                    for (y in i.y..i.y + 30) {
                        bitmap.setPixel(x, y, Color.BLACK)
                        if (y == bitmap.height - 1) break
                    }
                    if (x == bitmap.width - 1) break
                }
            }
        } else {
            for (i in points2) {
                for (x in i.x..i.x + 30) {
                    for (y in i.y..i.y + 30) {
                        bitmap.setPixel(x, y, Color.BLACK)
                        if (y == bitmap.height - 1) break
                    }
                    if (x == bitmap.width - 1) break
                }
            }
        }
    }

    private fun IntRange.random() =
            Random().nextDouble() + start

}
