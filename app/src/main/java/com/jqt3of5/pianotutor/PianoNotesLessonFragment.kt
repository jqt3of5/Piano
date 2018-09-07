package com.jqt3of5.pianotutor

import android.Manifest
import android.media.AudioFormat
import android.media.AudioRecord
import android.media.MediaRecorder
import android.media.MediaRecorder.AudioSource.MIC
import android.os.Bundle
import android.provider.MediaStore
import android.support.v4.app.ActivityCompat
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import com.paramsen.noise.Noise

class PianoNotesLessonFragment : Fragment() {

    lateinit var recorder : AudioRecord
    lateinit var recordingThread : Thread
    var isRecording : Boolean = false

    val sample_rate : Int = 44100
    val buffer_size : Int = AudioRecord.getMinBufferSize(sample_rate, AudioFormat.CHANNEL_IN_MONO, AudioFormat.ENCODING_PCM_FLOAT)

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_piano_notes_lesson, container, false)
        recorder = AudioRecord(MIC, sample_rate, AudioFormat.CHANNEL_IN_MONO, AudioFormat.ENCODING_PCM_FLOAT, buffer_size)
        return view
    }

    override fun onResume() {
        super.onResume()

        recordingThread = Thread(Runnable {

            isRecording = true

            val data : FloatArray = FloatArray(buffer_size/4)
            while(isRecording)
            {
                recorder.read(data, 0, buffer_size/4, AudioRecord.READ_BLOCKING)
                val noise = Noise.real().optimized().init(buffer_size/4, true)
                val fftData = noise.fft(data)

            }
            recorder.stop()
        })

        if (recorder.state == AudioRecord.STATE_INITIALIZED)
        {
            recorder.startRecording()
            recordingThread.start()
        }

    }

    override fun onPause() {
        super.onPause()
        isRecording = false
        recordingThread.join()
    }
}