package com.example.greenpoison.rocketbank.model.impl

import android.graphics.Bitmap
import android.graphics.Color
import android.graphics.Paint
import android.graphics.Point
import android.util.Log
import com.example.greenpoison.rocketbank.model.inter.IMainAModel
import com.example.greenpoison.rocketbank.presenter.callback.CallBack
import java.util.*
import android.os.AsyncTask
import kotlin.concurrent.thread


class MainAModelImpl : IMainAModel {
    override fun FillDeq(bitmap: Bitmap, x: Int, y: Int, number: Int, sleepTime: Int) {
        STOP_ASYNC = false
        this.rTask = MyTask().execute() as MyTask
        PointsStack.add(Point(x, y))
        cur_number = number
        FillDequeRecursive(bitmap, sleepTime)
    }
    fun FillDequeRecursive(bitmap: Bitmap, sleepTime : Int) {
        IS_ENDED = false
        thread(true, name = "FillDequeThread") {
            if (PointsStack.size == 0) {
                IS_ENDED = true
                STOP_ASYNC = true
                return@thread
            }
            val curPoint = PointsStack.pop()
            Log.e("DegueLog", "polled, x = ${curPoint.x}, y = ${curPoint.y}, size = ${PointsStack.size}")
            bitmap.setPixel(curPoint.x, curPoint.y, Color.RED)
            Thread.sleep((sleepTime).toLong())

            if (curPoint.y != bitmap.height - 1) {
                if (bitmap.getPixel(curPoint.x, curPoint.y + 1) == Color.BLACK) {
                    PointsStack.push(Point(curPoint.x, curPoint.y + 1))
                    Log.e("DegueLog", "added, x = ${curPoint.x}, y = ${curPoint.y+1}, size = ${PointsStack.size}")
                }
            }

            if (curPoint.y != 0) {
                if (bitmap.getPixel(curPoint.x, curPoint.y - 1) == Color.BLACK) {
                    PointsStack.push(Point(curPoint.x, curPoint.y - 1))
                    Log.e("DegueLog", "added, x = ${curPoint.x}, y = ${curPoint.y-1}, size = ${PointsStack.size}")
                }
            }


            if (curPoint.x != bitmap.width - 1) {
                if (bitmap.getPixel(curPoint.x + 1, curPoint.y) == (Color.BLACK)) {
                    PointsStack.push(Point(curPoint.x + 1, curPoint.y))
                    Log.e("DegueLog", "added, x = ${curPoint.x+1}, y = ${curPoint.y}, size = ${PointsStack.size}")
                }
            }

            if (curPoint.x != 0) {
                if (bitmap.getPixel(curPoint.x - 1, curPoint.y) == Color.BLACK) {
                    PointsStack.push(Point(curPoint.x - 1, curPoint.y))
                    Log.e("DegueLog", "added, x = ${curPoint.x-1}, y = ${curPoint.y}, size = ${PointsStack.size}")
                }
            }
            FillDequeRecursive(bitmap, sleepTime)
        }

    }

    var IS_ENDED = true


    fun registerCallback(callback: CallBack) {
        this.mCallBack = callback
    }

    private lateinit var rTask: MyTask
    var STOP_ASYNC = false
    lateinit var mCallBack: CallBack
    var p = Paint()
    var cur_number: Int = -1
    var PointsStack = Stack<Point>()
    var PointsDeque = ArrayDeque<Point>()

    internal inner class MyTask : AsyncTask<Void, Void, Void>() {
        override fun doInBackground(vararg params: Void?): Void? {
            var async_number = cur_number
            mCallBack.GetTaskStatus("RUNNING", async_number)
            while (true) {
                if (async_number == -1) break
                mCallBack.RequestView(1)
                mCallBack.RequestView(2)
                Thread.sleep(10)
                Log.e("ASYNC TASK", "I am working with number: $async_number")
                if (STOP_ASYNC) break
            }
            return null
        }

        override fun onPostExecute(result: Void?) {
            mCallBack.GetTaskStatus("FINISHED", cur_number)
            mCallBack.SetGenButtonCLickable()
            super.onPostExecute(result)
        }

        override fun onCancelled() {
            super.onCancelled()
            mCallBack.RequestView(cur_number)

        }
    }


    override fun FillSeed(bitmap: Bitmap, x: Int, y: Int, number: Int, sleepTime : Int) {
        STOP_ASYNC = false
        this.rTask = MyTask().execute() as MyTask
        PointsStack.push(Point(x, y))
        cur_number = number
        FillStackRecursive(bitmap, sleepTime)
    }
    fun FillStackRecursive(bitmap: Bitmap, sleepTime : Int) {
        IS_ENDED = false
        thread(true, name = "FillStackThread") {
            if (PointsStack.size == 0) {
                IS_ENDED = true
                STOP_ASYNC = true
                return@thread
            }
            val curPoint = PointsStack.pop()
            bitmap.setPixel(curPoint.x, curPoint.y, Color.RED)
            Thread.sleep((sleepTime).toLong())
            if (curPoint.x != bitmap.width - 1) {
                if (bitmap.getPixel(curPoint.x + 1, curPoint.y) == (Color.BLACK)) {
                    PointsStack.push(Point(curPoint.x + 1, curPoint.y))
                }
            }

            if (curPoint.x != 0) {
                if (bitmap.getPixel(curPoint.x - 1, curPoint.y) == Color.BLACK) {
                    PointsStack.push(Point(curPoint.x - 1, curPoint.y))
                }
            }

            if (curPoint.y != bitmap.height - 1) {
                if (bitmap.getPixel(curPoint.x, curPoint.y + 1) == Color.BLACK) {
                    PointsStack.push(Point(curPoint.x, curPoint.y + 1))
                }
            }

            if (curPoint.y != 0) {
                if (bitmap.getPixel(curPoint.x, curPoint.y - 1) == Color.BLACK) {
                    PointsStack.push(Point(curPoint.x, curPoint.y - 1))
                }
            }
            FillStackRecursive(bitmap, sleepTime)
        }

    }

    override fun DrawRandPoints(bitmap: Bitmap, number: Int) {
        var i = 0
        var j = 0
        while (i < bitmap.width - 1) {
            while (j < bitmap.height - 1) {
                val rand = (0..100).random()
                if (rand * 100 > 70) {
                    for (x in i..i + 29) {
                        for (y in j..j + 29) {
                            bitmap.setPixel(x, y, Color.BLACK)
                            if (y == bitmap.height - 1) break
                        }
                        if (x == bitmap.width - 1) break
                    }
                } else {
                    for (x in i..i + 29) {
                        for (y in j..j + 29) {
                            bitmap.setPixel(x, y, Color.LTGRAY)
                            if (y == bitmap.height - 1) break
                        }
                        if (x == bitmap.width - 1) break
                    }
                }
                j += 30
            }
            j = 0
            i += 30
        }
    }

    override fun RestorePoints(bitmap: Bitmap, number: Int) {
        mCallBack.RequestView(cur_number)
    }

    private fun IntRange.random() =
            Random().nextDouble() + start

}
