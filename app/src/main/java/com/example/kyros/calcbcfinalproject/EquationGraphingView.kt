package com.example.kyros.calcbcfinalproject

import android.content.Context
import android.content.SharedPreferences
import android.graphics.Canvas
import android.util.AttributeSet
import android.view.View
import kotlin.properties.Delegates

/**
 * Created by Kyros on 2/25/2018.
 */
class EquationGraphingView(context : Context, attributeSet: AttributeSet? = null) : View(context, attributeSet) {

    //constants
    companion object {
        const val UPPER_Y_KEY = "upper limit of y"
        const val LOWER_Y_KEY = "lower limit of y"
        const val UPPER_X_KEY = "upper limit of x"
        const val LOWER_X_KEY = "lower limit of x"
    }

    //limits of the graph
    var upperY : Double by Delegates.observable(10.0) {
        _, _, _ ->
        invalidate()
    }
    var lowerY : Double by Delegates.observable(-10.0) {
        _, _, _ ->
        invalidate()
    }
    var upperX: Double by Delegates.observable(10.0) {
        _, _, _ ->
        invalidate()
    }
    var lowerX : Double by Delegates.observable(-10.0) {
        _, _, _ ->
        invalidate()
    }

    //test equation
    private val testEquation : (Double) -> Double = {x -> Math.pow(x, 2.0)}

    override fun onDraw(canvas: Canvas?) {
        super.onDraw(canvas)

        //draw the equation

    }


}