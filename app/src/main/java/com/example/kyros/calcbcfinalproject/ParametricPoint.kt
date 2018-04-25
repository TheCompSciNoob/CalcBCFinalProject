package com.example.kyros.calcbcfinalproject

data class ParametricPoint (val t: Double, val x: Float, val y: Float) {
    override fun toString(): String = "t: ${"%.3f".format(t)}, x: ${"%.3f".format(x)}, y: ${"%.3f".format(y)}"
}