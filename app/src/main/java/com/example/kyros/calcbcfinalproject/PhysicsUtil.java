package com.example.kyros.calcbcfinalproject;

public class PhysicsUtil {
    private String x, y;
    private double a, b, c, d, e, f;

    public PhysicsUtil(String x, String y)
    {
        this.y = y;
        this.x = x;
    }
    //assigning values to a, b, c, d, e, and f
    public void parse()
    {
        a = Double.parseDouble(x.substring(0, x.indexOf("t^2")));
        b = Double.parseDouble(x.substring(x.indexOf("t^2")+4, x.indexOf("t+")));
        c = Double.parseDouble(x.substring(x.indexOf("t+")+2, x.length()));
        d = Double.parseDouble(y.substring(0, y.indexOf("t^2")));
        e = Double.parseDouble(y.substring(y.indexOf("t^2")+4, y.indexOf("t+")));
        f = Double.parseDouble(y.substring(y.indexOf("t+")+2, y.length()));
    }

    public double getVelocity(double t)
    {
        return ((2 * d * t) + e) / ((2 * a * t) + b);
    }

    public double getAcceleration(double t)
    {
        return ((((2 * a * t) + b) * (2 * d)) - (((2 * d * t) + e) * (2 * a))) / Math.pow(((2 * a * t) + b), 3);
    }
}
