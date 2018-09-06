package com.jqt3of5.pianotutor

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.graphics.drawable.Drawable
import android.support.constraint.ConstraintLayout
import android.text.TextPaint
import android.util.AttributeSet
import android.view.View
import android.widget.ImageView
import android.widget.TextView

/**
 * TODO: document your custom view class.
 */
class LessonView: ConstraintLayout {

    lateinit var imageView : ImageView
    lateinit var textView : TextView

    constructor(context : Context, attrSet: AttributeSet) : super(context, attrSet)
    {
        init()
    }


    private fun init()
    {
        View.inflate(context, R.layout.lesson_view, this)
        imageView = findViewById(R.id.lesson_imageView)
        textView = findViewById(R.id.lesson_textView)
    }


}
