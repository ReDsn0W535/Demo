package com.example.greenpoison.rocketbank.view.fragment


import android.content.Context
import android.os.Bundle
import android.support.v4.app.Fragment
import android.util.AttributeSet
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.greenpoison.rocketbank.R
import com.example.greenpoison.rocketbank.presenter.impl.MainAPresenterImpl
import com.example.greenpoison.rocketbank.presenter.inter.IMainAPresenter
import com.example.greenpoison.rocketbank.view.activity.MainActivity
import com.example.greenpoison.rocketbank.view.inter.IMainAView
import android.R.attr.bitmap
import android.opengl.ETC1.getHeight
import android.opengl.ETC1.getWidth
import android.R.attr.rotation
import android.graphics.*
import android.os.Build
import android.support.annotation.RequiresApi


private lateinit var act : MainActivity

class SurfFragment : Fragment() {
    lateinit var drawView: DrawView
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        act = activity as MainActivity
        var v = inflater.inflate(R.layout.fragment, null)
        drawView = v.findViewById(R.id.drawView)
        return v;
    }
}



class DrawView: View, IMainAView {
    override fun <T> request(requestFlag: Int): T? {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun <T> response(response: T, responseFlag: Int) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    constructor(context: Context?) : super(context)
    constructor(context: Context?, attrs: AttributeSet?) : super(context, attrs)
    constructor(context: Context?, attrs: AttributeSet?, defStyleAttr: Int) : super(context, attrs, defStyleAttr)
    constructor(context: Context?, attrs: AttributeSet?, defStyleAttr: Int, defStyleRes: Int) : super(context, attrs, defStyleAttr, defStyleRes)


    override fun onDraw(canvas: Canvas?) {


        var paint = Paint(Paint.ANTI_ALIAS_FLAG)
        var b : Bitmap = Bitmap.createBitmap(this.width,this.height,Bitmap.Config.ARGB_8888)
        var p = act.mIMainAPresenter!!.GetPoints(b)
        //Log.e("Draw Log", "bitmap parameters: ${b.width},  ${b.height}")
        val matrix = Matrix()
        matrix.setRotate(rotation, (b.width).toFloat(), (b.height).toFloat())
        canvas!!.drawBitmap(b, matrix, paint)
        super.onDraw(canvas)
    }




}