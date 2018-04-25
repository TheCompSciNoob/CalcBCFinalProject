package com.example.kyros.calcbcfinalproject;

public class PhysicsUtil {
//    private String x, y;
    private double a, b, c, d, e, f;

    public PhysicsUtil(double a, double b, double c, double d, double e, double f)
    {
        this.a = a;
        this.b = b;
        this.c = c;
        this.d = d;
        this.e = e;
        this.f = f;
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

    public double getVelocity(double t)
    {
        return ((2 * d * t) + e) / ((2 * a * t) + b);
    }

    public double getAcceleration(double t)
    {
        return ((((2 * a * t) + b) * (2 * d)) - (((2 * d * t) + e) * (2 * a))) / Math.pow(((2 * a * t) + b), 3);
    }

    public double getArcLength(double t)
    {
        double yValues = 0;

        for (int i = 0; i < 99; i++)
        {
            double tempTime = (t * ((i+1) / 100));
            double dx = 2 * a * tempTime + b;
            double dy = 2 * d * tempTime + e;
            yValues += (2 * java.lang.Math.sqrt(dx * dx + dy * dy));
        }


        //first term
        yValues += java.lang.Math.sqrt((4 * a * a * 0 * 0) + (4 * a * b * 0) + (b * b) + (4 * d * d * 0 * 0) + (4 * d * e * 0) + (e * e));
        //last term
        yValues += java.lang.Math.sqrt((4 * a * a * t * t) + (4 * a * b * t) + (b * b) + (4 * d * d * t * t) + (4 * d * e * t) + (e * e));

        return (0.5 * (t / 100) * (yValues));
    }

    public double getA() {
        return a;
    }

    public double getB() {
        return b;
    }

    public double getC() {
        return c;
    }

    public double getD() {
        return d;
    }

    public double getE() {
        return e;
    }

    public double getF() {
        return f;
    }
}
