package com.example.greenpoison.rocketbank.view.activity

import android.content.Context
import android.graphics.Bitmap
import android.graphics.Canvas
import android.graphics.Paint
import android.os.Bundle
import android.support.v7.app.AlertDialog
import android.support.v7.app.AppCompatActivity
import android.util.AttributeSet
import android.util.Log
import android.view.MotionEvent
import android.view.View
import android.widget.*
import android.widget.AdapterView.OnItemSelectedListener
import com.example.greenpoison.rocketbank.R
import com.example.greenpoison.rocketbank.presenter.impl.MainAPresenterImpl
import com.example.greenpoison.rocketbank.presenter.inter.IMainAPresenter
import com.example.greenpoison.rocketbank.view.fragment.DialogScreen
import com.example.greenpoison.rocketbank.view.inter.IMainAView
import android.widget.Toast
import android.widget.SeekBar
import android.widget.SeekBar.OnSeekBarChangeListener




enum class Action { FIRST_DRAW, SEEDALG, XORALG, INDEFINITELY, RESTORE }

var mIMainAPresenter: IMainAPresenter? = null
lateinit var sizeButton: Button
lateinit var generateButton: Button
var sleepTime : Int = 20


class MainActivity : AppCompatActivity(), IMainAView {


    lateinit var dialog: AlertDialog
    lateinit var drawView1: DrawView
    lateinit var drawView2: DrawView
    lateinit var spinner1: Spinner
    lateinit var spinner2: Spinner
    lateinit var seekBar: SeekBar
    override fun RequestView(number: Int) {
        if (this != null)
            if (number == 1) {
                drawView1.postInvalidate()
            } else drawView2.postInvalidate()
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        if (mIMainAPresenter == null) mIMainAPresenter = MainAPresenterImpl(this)
        setContentView(R.layout.activity_main)
        drawView1 = findViewById(R.id.drawView)
        drawView1.set_number(1)
        drawView2 = findViewById(R.id.drawView2)
        drawView2.set_number(2)
        spinner1 = findViewById(R.id.spinner)
        spinner2 = findViewById(R.id.spinner2)
        seekBar = findViewById(R.id.seekBar2)
        seekBar.setOnSeekBarChangeListener(object : OnSeekBarChangeListener {

            override fun onStopTrackingTouch(seekBar: SeekBar) {
                // TODO Auto-generated method stub
            }

            override fun onStartTrackingTouch(seekBar: SeekBar) {
                // TODO Auto-generated method stub
            }

            override fun onProgressChanged(seekBar: SeekBar, progress: Int, fromUser: Boolean) {
                // TODO Auto-generated method stub

                sleepTime -= progress
            }
        })
        val toast = Toast.makeText(applicationContext,
                "Не нажимайте на кнопку generate во время отрисовки изображения, это может привести к неправильной работе программы.", Toast.LENGTH_SHORT)
        toast.show()
        val itemSelectedListener1 = object : OnItemSelectedListener {
            override fun onItemSelected(parent: AdapterView<*>, view: View, position: Int, id: Long) {

                val item = parent.getItemAtPosition(position) as String
                drawView1.setFillType(item)
            }

            override fun onNothingSelected(parent: AdapterView<*>) {

            }
        }
        val itemSelectedListener2 = object : OnItemSelectedListener {
            override fun onItemSelected(parent: AdapterView<*>, view: View, position: Int, id: Long) {

                val item = parent.getItemAtPosition(position) as String
                drawView1.setFillType(item)
            }

            override fun onNothingSelected(parent: AdapterView<*>) {

            }
        }


        spinner1.onItemSelectedListener = itemSelectedListener1
        spinner2.onItemSelectedListener = itemSelectedListener2
        sizeButton = findViewById(R.id.button)
        sizeButton.setOnClickListener(oclBtnOk)
        generateButton = findViewById(R.id.button2)
        generateButton.setOnClickListener(printPicture)
    }

    var oclBtnOk: View.OnClickListener = object : View.OnClickListener {
        override fun onClick(v: View) {
            dialog = DialogScreen().getDialog(this@MainActivity)
            dialog.show()
            initSettings(dialog)
        }

    }
    var printPicture: View.OnClickListener = View.OnClickListener {
        drawView1.set_action(Action.FIRST_DRAW)
        drawView1.invalidate()
        drawView2.set_action(Action.FIRST_DRAW)
        drawView2.invalidate()
    }

    override fun SetGenButtonNonCLickable() {
        generateButton.isEnabled = false
    }

    override fun SetGenButtonCLickable() {
        generateButton.isEnabled = true
    }

    fun initSettings(d: AlertDialog) {
        var button = d.getButton(AlertDialog.BUTTON_POSITIVE)
        button.setOnClickListener {
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
            // Log.e("MAIN TAG", "${new_h},    ${new_w}")
            d.dismiss()
        }
        var button2 = d.getButton(AlertDialog.BUTTON_NEGATIVE)
        button2.setOnClickListener {
            d.dismiss()
        }
    }
}

class DrawView : ImageView {

    private var number: Int = 0
    private var cur_action: Action = Action.INDEFINITELY
    private var FillType: Action? = Action.SEEDALG
    @Volatile
    private lateinit var drawBitmap: Bitmap
    private var IS_BITMAP_CREATED = false

    constructor(context: Context?) : super(context)
    constructor(context: Context?, attrs: AttributeSet?) : super(context, attrs)
    constructor(context: Context?, attrs: AttributeSet?, defStyleAttr: Int) : super(context, attrs, defStyleAttr)
    constructor(context: Context?, attrs: AttributeSet?, defStyleAttr: Int, defStyleRes: Int) : super(context, attrs, defStyleAttr, defStyleRes)

    fun set_action(action: Action) {
        cur_action = action
    }

    fun set_number(i: Int) {
        number = i
    }

    fun setFillType(type: String) {
        if (type == "Алгоритм закраски с затравкой") FillType = Action.SEEDALG
        if (type == "Построчная XOR-обработка") FillType = Action.XORALG
    }

    override fun onDraw(canvas: Canvas?) {
        if (IS_BITMAP_CREATED == false) {
            this.drawBitmap = Bitmap.createBitmap(width, height, Bitmap.Config.ARGB_8888)
            IS_BITMAP_CREATED = true
        }
        if (cur_action == Action.RESTORE) canvas?.drawBitmap(this.drawBitmap, 0F, 0F, Paint())
        if (cur_action == Action.FIRST_DRAW) {
            drawBitmap = Bitmap.createBitmap(width, height, Bitmap.Config.ARGB_8888)
        }
        mIMainAPresenter?.Draw(this.drawBitmap, this.number, cur_action)
        if (canvas != null) {
            canvas.drawBitmap(this.drawBitmap, 0F, 0F, Paint())
        }
        cur_action = Action.INDEFINITELY
        super.onDraw(canvas)
    }

    override fun onTouchEvent(event: MotionEvent?): Boolean {
        if (event?.x?.toInt()!! < drawBitmap.width && event?.y?.toInt()!! < drawBitmap.height) {
            Log.e("Touch LOG", "x = ${event?.x?.toInt()}, y = ${event?.y?.toInt()}")
            if (event != null && FillType != null) {
                mIMainAPresenter?.Fill(drawBitmap, event.x.toInt(), event.y.toInt(), this.number, FillType!!, sleepTime)
            }
        }
        return super.onTouchEvent(event)
    }


}