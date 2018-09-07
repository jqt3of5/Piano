package com.jqt3of5.pianotutor

import android.Manifest
import android.content.Intent
import android.content.pm.PackageManager
import android.os.Bundle
import android.os.PersistableBundle
import android.support.v4.app.ActivityCompat
import android.support.v4.app.FragmentManager
import android.support.v4.content.ContextCompat
import android.support.v7.app.AppCompatActivity
import android.widget.ImageView

class LessonActivity : AppCompatActivity()
{
    enum class LessonEnum {
        PianoNotes,
        MusicNotes,
        Scales
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_lesson)

        val closer = findViewById<ImageView>(R.id.close_lesson_image)
        closer.setOnClickListener {
            finish()
        }

        intent.getStringExtra("lesson")?.let {

            when(LessonEnum.valueOf(it))
            {
                LessonEnum.PianoNotes -> supportFragmentManager.beginTransaction().add(R.id.fragment_container,PianoNotesLessonFragment()).commit()
                LessonEnum.MusicNotes -> supportFragmentManager.beginTransaction().add(R.id.fragment_container,MusicNotesLessonFragment()).commit()
                LessonEnum.Scales -> return
            }
        }
    }
}