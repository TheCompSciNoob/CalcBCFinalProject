package com.example.kyros.calcbcfinalproject

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class ParametricPoint (val t: Double, val x: Float, val y: Float) : Parcelable {
    override fun toString(): String = "t: ${"%.3f".format(t)}, x: ${"%.3f".format(x)}, y: ${"%.3f".format(y)}"
}

/*
fun createTree(depth: Int, t: Double, size: Double) {
    fun subdivide(depth: Int, t: Double, size: Double) {
    	createTree(depth + 1, t, size / 2)
    	createTree(depth + 1, t + size / 2, size / 2)
	}
    if (depth < searchDepth || radius(t) < nMult * size) {
        //subdivide if:
        //searchDepth has not been reached
        //not flat enough
        subdivide(depth, t, size)
    }
    else {
        points.add(ParametricPoint(t, getX(0, t), getY(0, t)))
    }
}

fun radius(t: Double) : Double
	//radius of curvature
	= (getX(1, t).pow(2.0) + getY(1, t).pow(2.0)).pow(1.5) /
	abs(getX(1, t) * getY(2, t) - getY(1, t) * getX(2, t))

fun getX(nDerive: Int, t: Double) : Double = when(nDerive) {
    //temporary, for demonstration
    0 -> t
    1 -> 1.0
    2 -> 0.0
    else -> throw IllegalStateException("cannot evaluate derivative order >2")
}

fun getY(nDerive: Int, t : Double) : Double = when (nDerive) {
    //temporary, for demonstration
    0 -> 30 * (t-3).pow(2)
    1 -> 30 * 2 * (t-3)
    2 -> 30 * 2.0
    else -> throw IllegalStateException("cannot evaluate derivative order >2")
}
*/
