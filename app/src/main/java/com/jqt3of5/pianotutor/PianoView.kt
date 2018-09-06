package com.jqt3of5.pianotutor

import android.content.Context
import android.graphics.Canvas
import android.graphics.Paint
import android.util.AttributeSet
import android.view.MotionEvent
import android.view.View

class PianoView(context : Context, attrSet : AttributeSet) : View(context, attrSet) {

    var whiteKeyWidth : Float = 0f
    var blackKeyWidth : Float = 0f
    var blackKeyHeight : Float = 0f

    override fun onDraw(canvas: Canvas?) {
        super.onDraw(canvas)

        canvas?.apply {
            //Draw first line
            drawLine(0f, 0f, 0f, height.toFloat(), Paint(Paint.ANTI_ALIAS_FLAG))
            //Draw Last Line
            drawLine(width.toFloat(), 0f, width.toFloat(), height.toFloat(), Paint(Paint.ANTI_ALIAS_FLAG))
            //Draw middle line
            drawLine(3 * whiteKeyWidth, 0f, 3 * whiteKeyWidth, height.toFloat(), Paint(Paint.ANTI_ALIAS_FLAG))


        }
    }

    override fun onSizeChanged(w: Int, h: Int, oldw: Int, oldh: Int) {
        super.onSizeChanged(w, h, oldw, oldh)
        whiteKeyWidth = w / 7f
        blackKeyWidth = whiteKeyWidth / 2f
        blackKeyHeight = h / 2f
    }

    override fun onTouchEvent(event: MotionEvent?): Boolean {
        return super.onTouchEvent(event)
    }
}