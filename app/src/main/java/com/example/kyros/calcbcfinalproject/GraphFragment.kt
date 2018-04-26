package com.example.kyros.calcbcfinalproject

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.webkit.JavascriptInterface
import android.webkit.WebChromeClient
import android.webkit.WebViewClient
import kotlinx.android.synthetic.main.graph_fragment.*
import org.json.JSONArray
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

    private lateinit var parametricPoints: List<ParametricPoint>

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.graph_fragment, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        /*val physicsUtil = arguments?.let {
            PhysicsUtil(it.getDouble(X_A), it.getDouble(X_B), it.getDouble(X_C), it.getDouble(Y_A), it.getDouble(Y_B), it.getDouble(Y_C))
        }
        val equation = "(${physicsUtil?.getXParametric()},${physicsUtil?.getYParametric()})"
        Log.d(TAG, "Equation: $equation")*/
        parametricPoints = arguments?.getParcelableArrayList(PARAMETRIC_POINTS_KEY)!!
        graphing_view.apply {
            settings.javaScriptEnabled = true
            addJavascriptInterface(this@GraphFragment, "equationGetter")
            requestFocusFromTouch()
            webViewClient = WebViewClient()
            webChromeClient = WebChromeClient()
            loadUrl("file:///android_res/raw/desmos_graphing.html")
        }
    }

    @JavascriptInterface
    fun getTArray()  = parametricPoints.map { it.t.toString() }.let { JSONArray(it).toString() }

    @JavascriptInterface
    fun getXArray() = parametricPoints.map { it.x.toString() }.let { JSONArray(it).toString() }

    @JavascriptInterface
    fun getYArray() = parametricPoints.map { it.y.toString() }.let { JSONArray(it).toString() }

    class EquationGetter (val points: List<ParametricPoint>) {


    }

    private fun PhysicsUtil.getXParametric() = "${a.roundToInt()}t^2+${b.roundToInt()}t+${c.roundToInt()}"

    private fun PhysicsUtil.getYParametric() = "${d.roundToInt()}t^2+${e.roundToInt()}t+${f.roundToInt()}"
}