package com.jqt3of5.pianotutor.Recognition

import android.media.AudioRecord
import com.paramsen.noise.Noise
import kotlin.math.atan2
import kotlin.math.sqrt

class AudioNoteRecognizer(var recorder : AudioRecord)
{
    class Wave (val real : Float, val imaginary : Float, val frequency : Float) {

        val amplitude : Float
            get () = amplitude(real, imaginary)

        val phase : Float
                get () = atan2(real, imaginary)

        companion object {
            fun amplitude(real : Float, imaginary : Float) : Float
            {
                return sqrt(real*real + imaginary*imaginary)
            }

            fun frequency(index : Int, N : Int, sampleRate : Int) : Float
            {
                return index.toFloat() * sampleRate.toFloat() / N.toFloat()
            }
        }
    }

    val fft_buffer_size : Int = 1024
    var rawSoundData : FloatArray? = null
    var waveData : List<Wave>? = null

    fun didRecognizeNotes() : Boolean
    {
        return true
    }

    fun recognizedNotes() : List<PianoKey>
    {
        return listOf(PianoKey.A4)
    }

    fun listen()
    {
        rawSoundData = FloatArray(fft_buffer_size)
        recorder.read(rawSoundData, 0, rawSoundData!!.size, AudioRecord.READ_BLOCKING)

        val noise = Noise.real().optimized().init(rawSoundData!!.size, true)
        //The output is real,imaginary. Zip each pair together.
        val fftData = noise.fft(rawSoundData).toList().zipWithNext()

        waveData = fftData.map {
            val index = fftData.indexOf(it)
            val frequency = Wave.frequency(index, fftData.size, recorder.sampleRate)
            Wave(it.first, it.second, frequency)
        }

        process(waveData!!)

    }

    fun process(data : List<Wave>)
    {
        //Find peaks of Distributions using Expectation maxmimization

        //Match average value of each peak to specific note
    }

}