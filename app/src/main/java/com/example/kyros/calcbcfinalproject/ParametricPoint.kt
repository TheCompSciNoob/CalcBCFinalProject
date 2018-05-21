package com.example.kyros.calcbcfinalproject

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize
import kotlin.math.abs
import kotlin.math.pow

@Parcelize
data class ParametricPoint(val t: Double, val x: Float, val y: Float) : Parcelable {
    override fun toString(): String = "t: ${"%.3f".format(t)}, x: ${"%.3f".format(x)}, y: ${"%.3f".format(y)}"
}

public fun PhysicsUtil.createPlotPoints(depth: Int, ti: Double, duration: Double): List<ParametricPoint> = mutableListOf<ParametricPoint>().apply {
    createTree(this, 5, 5, depth, ti, duration)
    add(ParametricPoint(ti + duration, getX(ti + duration).toFloat(), getY(ti + duration).toFloat()))
    sortBy { it.t }
}

private fun PhysicsUtil.createTree(points: MutableList<ParametricPoint>, searchDepth: Int, nMult: Int, depth: Int, t: Double, duration: Double) {

    fun radius(t: Double): Double
            = (getXV(t).pow(2.0) + getYV(t).pow(2.0)).pow(1.5) /
            abs(getXV(t) * getYA(t) - getYV(t) * getXA(t))

    if (depth < searchDepth || radius(t) < nMult * duration) {
        //subdivide if:
        //searchDepth has not been reached
        //not flat enough
        createTree(points, searchDepth, nMult, depth + 1, t, duration / 2)
        createTree(points, searchDepth, nMult,depth + 1, t + duration / 2, duration / 2)
    } else {
        points.add(ParametricPoint(t, getX(t).toFloat(), getY(t).toFloat()))
    }
}