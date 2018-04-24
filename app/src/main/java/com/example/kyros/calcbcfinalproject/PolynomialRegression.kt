package com.example.kyros.calcbcfinalproject

import android.util.Log

import java.util.Arrays
import java.util.function.IntToDoubleFunction
import java.util.stream.IntStream

import android.content.ContentValues.TAG

/**
 * Created by Max on 4/20/2018.
 */

object PolynomialRegression {
    fun polyRegression(x: IntArray, y: IntArray): DoubleArray {
        val n = x.size
        val r = IntStream.range(0, n).toArray()
        val xm = Arrays.stream(x).average().orElse(java.lang.Double.NaN)
        val ym = Arrays.stream(y).average().orElse(java.lang.Double.NaN)
        val x2m = Arrays.stream(r).map { a -> a * a }.average().orElse(java.lang.Double.NaN)
        val x3m = Arrays.stream(r).map { a -> a * a * a }.average().orElse(java.lang.Double.NaN)
        val x4m = Arrays.stream(r).map { a -> a * a * a * a }.average().orElse(java.lang.Double.NaN)
        var xym = 0.0
        run {
            var i = 0
            while (i < x.size && i < y.size) {
                xym += (x[i] * y[i]).toDouble()
                ++i
            }
        }
        xym /= Math.min(x.size, y.size).toDouble()
        var x2ym = 0.0
        run {
            var i = 0
            while (i < x.size && i < y.size) {
                x2ym += (x[i] * x[i] * y[i]).toDouble()
                ++i
            }
        }
        x2ym /= Math.min(x.size, y.size).toDouble()

        val sxx = x2m - xm * xm
        val sxy = xym - xm * ym
        val sxx2 = x3m - xm * x2m
        val sx2x2 = x4m - x2m * x2m
        val sx2y = x2ym - x2m * ym

        val b = (sxy * sx2x2 - sx2y * sxx2) / (sxx * sx2x2 - sxx2 * sxx2)
        val c = (sx2y * sxx - sxy * sxx2) / (sxx * sx2x2 - sxx2 * sxx2)
        val a = ym - b * xm - c * x2m

        val abc = { xx: Int -> a + b * xx + c * xx.toDouble() * xx.toDouble() }


        Log.d(TAG, "polyRegression: " + " Input  Approximation")
        Log.d(TAG, "polyRegression: x   y     y1")
        for (i in 0 until n) {
            Log.d(TAG, "polyRegression: " + String.format("%2d %3d  %5.1f\n", x[i], y[i], abc(x[i])))
        }
        return doubleArrayOf(a, b, c)
    }
}
