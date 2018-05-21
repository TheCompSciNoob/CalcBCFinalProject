package com.example.kyros.calcbcfinalproject

import kotlin.math.pow

class PhysicsUtil( // x = a*t^2 + b*t + c, y = d*t^2 + e*t + f
        val a: Double, val b: Double, val c: Double, val d: Double, val e: Double, val f: Double) {
    //assigning values to a, b, c, d, e, and f
    //    public void parse()
    //    {
    //        a = Double.parseDouble(x.substring(0, x.indexOf("t^2")));
    //        b = Double.parseDouble(x.substring(x.indexOf("t^2")+4, x.indexOf("t+")));
    //        c = Double.parseDouble(x.substring(x.indexOf("t+")+2, x.length()));
    //        d = Double.parseDouble(y.substring(0, y.indexOf("t^2")));
    //        e = Double.parseDouble(y.substring(y.indexOf("t^2")+4, y.indexOf("t+")));
    //        f = Double.parseDouble(y.substring(y.indexOf("t+")+2, y.length()));
    //    }

    fun getX(t: Double) = a * t.pow(2) + b * t + c

    fun getY(t: Double) = d * t.pow(2) + e * t + f

    fun getVelocity(t: Double) = (2 * d * t + e) / (2.0 * a * t + b)

    fun getAcceleration(t: Double) = ((2 * a * t + b) * (2 * d) - (2 * d * t + e) * (2 * a)) / (2 * a * t + b).pow(3)

    fun getXV(t: Double) = 2 * a * t + b

    fun getYV(t: Double) = 2 * d * t + e

    fun getXA(t: Double) = 2 * a

    fun getYA(t: Double) = 2 * d

    fun getArcLength(t: Double): Double {

        //the first and last terms that are not doubled for trapezoidal
        var yValues = Math.sqrt(Math.pow(b, 2.0) + Math.pow(d, 2.0)) + Math.sqrt(Math.pow(2.0 * a * t, 2.0) + Math.pow(d, 2.0))

        for (i in 0..98) {
            val temp = t * (i + 1) / 100
            val dy = 2.0 * temp * a + b
            val dx = d


            //each term afterwards multiplied by 2
            yValues += 2 * Math.sqrt(Math.pow(dx, 2.0) + Math.pow(dy, 2.0))
        }


        return yValues / 2 * t / 100
    }
}
