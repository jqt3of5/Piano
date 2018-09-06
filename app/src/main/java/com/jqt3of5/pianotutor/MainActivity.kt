package com.jqt3of5.pianotutor

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        var musicNotesLessonView = findViewById<LessonView>(R.id.lesson_view_music_notes)
        musicNotesLessonView.textView.text = "Learn to recognize musical notes on sheet music"
        musicNotesLessonView.setOnClickListener {
            val intent = Intent(this, LessonActivity::class.java)
            intent.putExtra("lesson", LessonActivity.LessonEnum.MusicNotes.toString())
            startActivity(intent)
        }

        var pianoNotesLessonView = findViewById<LessonView>(R.id.lesson_view_piano_notes)
        pianoNotesLessonView.textView.text = "Lean the notes associated to each piano key"
        pianoNotesLessonView.setOnClickListener {
            val intent = Intent(this, LessonActivity::class.java)
            intent.putExtra("lesson", LessonActivity.LessonEnum.PianoNotes.toString())
            startActivity(intent)
        }
    }
}


