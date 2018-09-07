package com.jqt3of5.pianotutor

import android.media.AudioFormat
import android.media.AudioRecord
import android.media.MediaRecorder.AudioSource.MIC
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.jqt3of5.pianotutor.Recognition.AudioNoteRecognizer

class PianoNotesLessonFragment : Fragment() {

    lateinit var recorder : AudioRecord
    lateinit var recordingThread : Thread
    var isRecording : Boolean = false

    val sample_rate : Int = 44100
    val buffer_size : Int = AudioRecord.getMinBufferSize(sample_rate, AudioFormat.CHANNEL_IN_MONO, AudioFormat.ENCODING_PCM_FLOAT)

    lateinit var noteTextView : TextView

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_piano_notes_lesson, container, false)
        noteTextView = view.findViewById<TextView>(R.id.note)

        recorder = AudioRecord(MIC, sample_rate, AudioFormat.CHANNEL_IN_MONO, AudioFormat.ENCODING_PCM_FLOAT, buffer_size)
        return view
    }

    override fun onResume() {
        super.onResume()

        if (recorder.state == AudioRecord.STATE_INITIALIZED)
        {
            recorder.startRecording()

            recordingThread = Thread(Runnable {
                listeningThread()
            })
            recordingThread.start()
        }

    }

    override fun onPause() {
        super.onPause()
        isRecording = false
        recordingThread.join()
    }

    private fun listeningThread()
    {
        isRecording = true
        val recognizer = AudioNoteRecognizer(recorder)
        try {
            while(isRecording)
            {
                recognizer.listen()
                if (recognizer.didRecognizeNotes())
                {
                    val notes = recognizer.recognizedNotes()
                    noteTextView.text = notes.first().note.toString()
                }
            }
        }
        finally {
            recorder.stop()
        }
    }
}