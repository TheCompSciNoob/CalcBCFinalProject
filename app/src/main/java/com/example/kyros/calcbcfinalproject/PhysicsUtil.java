package com.example.kyros.calcbcfinalproject;

public class PhysicsUtil {
    //    private String x, y;
    private double a, b, c, d, e;

    public PhysicsUtil(double a, double b, double c, double d, double e) {
        this.a = a;
        this.b = b;
        this.c = c;
        this.d = d;
        this.e = e;
    }
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

    public double getVelocity(double t) {
        return ((2 * a * t) + b) / (d * t);
    }

    public double getAcceleration(double t) {

        //TODO: check
        return ((2 * a * t) + b) * (2 * a) / d;
    }

    public double getArcLength(double t) {

        //the first and last terms that are not doubled for trapezoidal
        double yValues = Math.sqrt(Math.pow(b, 2) + Math.pow(d, 2)) + Math.sqrt(Math.pow(2 * a * t, 2) + Math.pow(d, 2));

        //TODO: check if correct
        for (int i = 0; i < 99; i++) {
            double temp = t * (i + 1) / 100;
            double dy = (2 * temp * a) + b;
            double dx = (d);


            //each term afterwards multiplied by 2
            yValues += 2 * (Math.sqrt(Math.pow(dx, 2) + Math.pow(dy, 2)));
        }


        double finalValue = yValues / 2 * t / 100;

        return finalValue;
    }
}
