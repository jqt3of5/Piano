package com.jqt3of5.pianotutor

import android.content.Intent
import android.os.Bundle
import android.os.PersistableBundle
import android.support.v4.app.FragmentManager
import android.support.v7.app.AppCompatActivity

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