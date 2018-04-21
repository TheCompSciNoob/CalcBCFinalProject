package com.example.kyros.calcbcfinalproject;

import android.util.Log;

import java.util.Arrays;
import java.util.function.IntToDoubleFunction;
import java.util.stream.IntStream;

import static android.content.ContentValues.TAG;

/**
 * Created by Max on 4/20/2018.
 */

public class PolynomialRegression {
    public static double[] polyRegression(int[] x, int[] y) {
        int n = x.length;
        int[] r = IntStream.range(0, n).toArray();
        double xm = Arrays.stream(x).average().orElse(Double.NaN);
        double ym = Arrays.stream(y).average().orElse(Double.NaN);
        double x2m = Arrays.stream(r).map(a -> a * a).average().orElse(Double.NaN);
        double x3m = Arrays.stream(r).map(a -> a * a * a).average().orElse(Double.NaN);
        double x4m = Arrays.stream(r).map(a -> a * a * a * a).average().orElse(Double.NaN);
        double xym = 0.0;
        for (int i = 0; i < x.length && i < y.length; ++i) {
            xym += x[i] * y[i];
        }
        xym /= Math.min(x.length, y.length);
        double x2ym = 0.0;
        for (int i = 0; i < x.length && i < y.length; ++i) {
            x2ym += x[i] * x[i] * y[i];
        }
        x2ym /= Math.min(x.length, y.length);

        double sxx = x2m - xm * xm;
        double sxy = xym - xm * ym;
        double sxx2 = x3m - xm * x2m;
        double sx2x2 = x4m - x2m * x2m;
        double sx2y = x2ym - x2m * ym;

        double b = (sxy * sx2x2 - sx2y * sxx2) / (sxx * sx2x2 - sxx2 * sxx2);
        double c = (sx2y * sxx - sxy * sxx2) / (sxx * sx2x2 - sxx2 * sxx2);
        double a = ym - b * xm - c * x2m;

        IntToDoubleFunction abc = (int xx) -> a + b * xx + c * xx * xx;


        Log.d(TAG, "polyRegression: "+" Input  Approximation");
        Log.d(TAG, "polyRegression: x   y     y1");
        for (int i = 0; i < n; ++i) {
            Log.d(TAG, "polyRegression: "+String.format("%2d %3d  %5.1f\n", x[i], y[i], abc.applyAsDouble(x[i])));
        }
        return new double[]{a,b,c};
    }
}
