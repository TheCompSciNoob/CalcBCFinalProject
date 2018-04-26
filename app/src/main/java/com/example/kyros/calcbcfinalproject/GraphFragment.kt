package com.example.kyros.calcbcfinalproject

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.webkit.JavascriptInterface
import kotlinx.android.synthetic.main.graph_fragment.*
import kotlin.math.roundToInt

class GraphFragment : Fragment() {

    companion object {
        /*const val X_A = "x, A"
        const val X_B = "x, B"
        const val X_C = "x, c"
        const val Y_A = "y, A"
        const val Y_B = "y, B"
        const val Y_C = "y, c"*/
        private const val TAG = "GraphFragment"
        const val PARAMETRIC_POINTS_KEY = "parametric points key"
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.graph_fragment, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        /*val physicsUtil = arguments?.let {
            PhysicsUtil(it.getDouble(X_A), it.getDouble(X_B), it.getDouble(X_C), it.getDouble(Y_A), it.getDouble(Y_B), it.getDouble(Y_C))
        }
        val equation = "(${physicsUtil?.getXParametric()},${physicsUtil?.getYParametric()})"
        Log.d(TAG, "Equation: $equation")*/
        val parametricPoints = arguments?.getParcelableArrayList<ParametricPoint>(PARAMETRIC_POINTS_KEY) ?: emptyList<ParametricPoint>()
        graphing_view.apply {
            settings.javaScriptEnabled = true
            addJavascriptInterface(EquationGetter(parametricPoints), "equationGetter")
            loadUrl("file:///android_res/raw/desmos_graphing.html")
        }
    }

    class EquationGetter (val points: List<ParametricPoint>) {

        @JavascriptInterface
        fun getTArray() = points.map { it.t.toString() }.toTypedArray()

        @JavascriptInterface
        fun getXArray() = points.map { it.x.toString() }.toTypedArray()

        @JavascriptInterface
        fun getYArray() = points.map { it.y.toString() }.toTypedArray()
    }

    private fun PhysicsUtil.getXParametric() = "${a.roundToInt()}t^2+${b.roundToInt()}t+${c.roundToInt()}"

    private fun PhysicsUtil.getYParametric() = "${d.roundToInt()}t^2+${e.roundToInt()}t+${f.roundToInt()}"
}