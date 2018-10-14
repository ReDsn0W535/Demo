package com.example.greenpoison.rocketbank.view.activity

import android.app.PendingIntent.getActivity
import android.content.Context
import android.content.DialogInterface
import android.graphics.Canvas
import android.os.Bundle
import android.support.v7.app.AlertDialog
import android.support.v7.app.AppCompatActivity
import android.util.AttributeSet
import android.view.View
import android.widget.Button
import android.widget.Toast
import com.example.greenpoison.rocketbank.R
import com.example.greenpoison.rocketbank.presenter.impl.MainAPresenterImpl
import com.example.greenpoison.rocketbank.presenter.inter.IMainAPresenter
import com.example.greenpoison.rocketbank.view.inter.IMainAView
import android.content.DialogInterface.OnClickListener
import android.util.Log
import android.util.TypedValue
import android.view.LayoutInflater
import android.widget.EditText
import com.example.greenpoison.rocketbank.view.fragment.DialogScreen

var PERMISSION_OBTAINED = false
var mIMainAPresenter: IMainAPresenter? = null
class MainActivity : AppCompatActivity(), IMainAView {
    lateinit var dialog : AlertDialog
    lateinit var drawView1 : DrawView
    lateinit var drawView2 : DrawView

    override fun <T> request(requestFlag: Int): T? {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun <T> response(response: T, responseFlag: Int) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mIMainAPresenter = MainAPresenterImpl(this)
        setContentView(R.layout.activity_main)
        drawView1 = findViewById(R.id.drawView)
        drawView2 = findViewById(R.id.drawView2)
        var sizeButton = findViewById<Button>(R.id.button)
        sizeButton.setOnClickListener(oclBtnOk)
        var generateButton = findViewById<Button>(R.id.button2)
        generateButton.setOnClickListener(printPicture)
    }

    var oclBtnOk: View.OnClickListener = object : View.OnClickListener{
        override fun onClick(v: View) {
            dialog = DialogScreen().getDialog(this@MainActivity)
            dialog.show()
            initSettings(dialog)
        }

    }
    var printPicture: View.OnClickListener = object : View.OnClickListener{
        override fun onClick(v: View?) {
            PERMISSION_OBTAINED = true
            drawView1.invalidate()
            drawView2.invalidate()
            //PERMISSION_OBTAINED = false
        }
    }
    fun initSettings(d : AlertDialog){
        var button = d.getButton(AlertDialog.BUTTON_POSITIVE)
        button.setOnClickListener{
            var new_h = d.findViewById<EditText>(R.id.editHeight)?.text.toString().toInt()
            var new_w = d.findViewById<EditText>(R.id.editWidth)?.text.toString().toInt()
            var params = drawView1.layoutParams
            params.width = new_w
            params.height = new_h
            drawView1.requestLayout()
            params = drawView2.layoutParams
            params.width = new_w
            params.height = new_h
            drawView1.requestLayout()
            Log.e("MAIN TAG", "${new_h},    ${new_w}")
            d.dismiss()
        }
        var button2 = d.getButton(AlertDialog.BUTTON_NEGATIVE)
        button2.setOnClickListener{
            d.dismiss()
        }
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
        if (PERMISSION_OBTAINED == true)
            mIMainAPresenter!!.GetPoints(canvas!!, false)
        super.onDraw(canvas)
    }
}