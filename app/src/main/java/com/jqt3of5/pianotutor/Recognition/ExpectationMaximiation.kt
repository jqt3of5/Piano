package com.jqt3of5.pianotutor.Recognition

class GaussianCurve(val mean : Float, val standardDeviation : Float, val weight : Float)
//http://www.aishack.in/tutorials/expectation-maximization-gaussian-mixture-model-mixtures/
class ExpectationMaximiation(val numberOfCurves : Int, val data : FloatArray)
{
    //Create the specified number of curves initialized with pretty simple data
    var curves : List<GaussianCurve> = List<GaussianCurve>(numberOfCurves) {
        GaussianCurve(it.toFloat() * data.size/numberOfCurves, 0.1f * data.size, 1f/numberOfCurves)
    }

    //Create the responsibilitiy matrix  with a data point for each curve and input data.
    val responsibility : List<FloatArray> = List<FloatArray>(numberOfCurves) {
        FloatArray(data.size)
    }


}

