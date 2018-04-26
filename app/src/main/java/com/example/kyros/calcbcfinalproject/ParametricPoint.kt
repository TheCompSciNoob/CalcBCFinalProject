package com.example.kyros.calcbcfinalproject

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class ParametricPoint (val t: Double, val x: Float, val y: Float) : Parcelable {
    override fun toString(): String = "t: ${"%.3f".format(t)}, x: ${"%.3f".format(x)}, y: ${"%.3f".format(y)}"
}