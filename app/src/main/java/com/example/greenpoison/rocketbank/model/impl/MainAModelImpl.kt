package com.example.greenpoison.rocketbank.model.impl

import android.drm.DrmStore
import android.graphics.Bitmap
import android.graphics.Color
import android.graphics.Paint
import android.graphics.Point
import android.util.Log
import com.example.greenpoison.rocketbank.model.inter.IMainAModel
import com.example.greenpoison.rocketbank.presenter.callback.CallBack
import java.util.*
import kotlin.collections.ArrayList
import android.os.AsyncTask
import kotlin.concurrent.thread


class MainAModelImpl : IMainAModel {
    var IS_ENDED = true
    override fun FillXOR(bitmap: Bitmap, x: Int, y: Int, number: Int) {
        IS_ENDED = false
        this.rTask = MyTask().execute() as MyTask
        thread {
            if (PointsDeque.size == 0) {
                IS_ENDED = true
                STOP_ASYNC = true
                return@thread
            }
            var cur_point = PointsDeque.pop()
            bitmap.setPixel(cur_point.x, cur_point.y, Color.RED)
            if (cur_point.x != bitmap.width - 1) {
                if (bitmap.getPixel(cur_point.x + 1, cur_point.y) == (Color.BLACK)) {
                    PointsDeque.push(Point(cur_point.x + 1, cur_point.y))
                }
            }

            if (cur_point.x != 0) {
                if (bitmap.getPixel(cur_point.x - 1, cur_point.y) == Color.BLACK) {
                    PointsDeque.push(Point(cur_point.x - 1, cur_point.y))
                }
            }

            if (cur_point.y != bitmap.height - 1) {
                if (bitmap.getPixel(cur_point.x, cur_point.y + 1) == Color.BLACK) {
                    PointsDeque.push(Point(cur_point.x, cur_point.y + 1))
                }
            }

            if (cur_point.y != 0) {
                if (bitmap.getPixel(cur_point.x, cur_point.y - 1) == Color.BLACK) {
                    PointsDeque.push(Point(cur_point.x, cur_point.y - 1))
                }
            }
            FillPointsRecursive(bitmap)
            Thread.sleep(20)
        }
           /* var startPoint = Point(x,y)
            var top = startPoint
            var bottom= startPoint
            var left = startPoint
            var right = startPoint
            while (bitmap.getPixel(top.x,top.y+1) != Color.WHITE && y != bitmap.height-1){
                top.y++
            }
            while (bitmap.getPixel(bottom.x,bottom.y-1) != Color.WHITE && y != 0){
                bottom.y--
            }
            while (bitmap.getPixel(left.x-1,left.y) != Color.WHITE && x!= 0){
                left.x--
            }
            while (bitmap.getPixel(right.x+1,right.y) != Color.WHITE && x != bitmap.height-1){
                right.x++
            }
            for (i in left.x..right.x) bitmap.setPixel(i,right.y,Color.RED)
            for (i in bottom.y..top.y) bitmap.setPixel(bottom.x,right.y,Color.RED)

            for (i in left.x..right.x) {
                bitmap.setPixel(i, left.x, Color.RED)

            }*/
        IS_ENDED = true
        STOP_ASYNC = true
    }

    fun registerCallback(callback: CallBack) {
        this.mCallBack = callback
    }

    private lateinit var rTask: MyTask
    var STOP_ASYNC = false
    var points1 = ArrayList<Point>(0)
    var points2 = ArrayList<Point>(0)
    lateinit var mCallBack: CallBack
    var p = Paint()
    var cur_number: Int = -1
    var PointsStack = Stack<Point>()
    var PointsDeque = ArrayDeque<Point>()

    override fun FillSeed(bitmap: Bitmap, x: Int, y: Int, number: Int) {
        mCallBack.SetGenButtonNonCLickable()
        if (bitmap.getPixel(x, y) == Color.WHITE) {
            Log.e("FILLSEED_TAG", "НЕЛЬЗЯ НАЖИМАТЬ НА БЕЛУЮ ОБЛАСТЬ")
            return
        }
        this.rTask = MyTask().execute() as MyTask
        PointsStack.push(Point(x, y))
        cur_number = number
        mCallBack.SetGenButtonNonCLickable()
        FillPointsRecursive(bitmap)
    }

    internal inner class MyTask : AsyncTask<Void, Void, Void>() {
        override fun doInBackground(vararg params: Void?): Void? {
            var async_numer = cur_number
            while (true) {

               /* if (async_numer != cur_number) {
                    async_numer = cur_number
                }*/
                mCallBack.RequestView(1)
                mCallBack.RequestView(2)
                Thread.sleep(10)
                Log.e("ASYNC TASK", "I am working with number: $async_numer")
                if (STOP_ASYNC) break
            }
            return null
        }

        override fun onPostExecute(result: Void?) {
            mCallBack.SetGenButtonCLickable()
            super.onPostExecute(result)
        }

        override fun onCancelled() {
            mCallBack.RequestView(cur_number)
            super.onCancelled()
        }
    }


    fun FillPointsRecursive(bitmap: Bitmap) {
        IS_ENDED = false
        thread(true, name = "FillPointsThread") {
            if (PointsStack.size == 0) {
                IS_ENDED = true
                STOP_ASYNC = true
                return@thread
            }
            var cur_point = PointsStack.pop()
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
            FillPointsRecursive(bitmap)
            Thread.sleep(20)
        }

    }

    override fun DrawRandPoints(bitmap: Bitmap, number: Int) {
        var points = ArrayList<Point>(0)
        var i = 0
        var j = 0
        while (i < bitmap.width - 1) {
            while (j < bitmap.height - 1) {
                var rand = (0..100).random()
                if (rand * 100 > 70) {
                    for (x in i..i + 29) {
                        for (y in j..j + 29) {
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
        mCallBack.RequestView(cur_number)
    }

    private fun IntRange.random() =
            Random().nextDouble() + start

}
