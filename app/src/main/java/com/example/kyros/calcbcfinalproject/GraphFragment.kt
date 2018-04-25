package com.example.kyros.calcbcfinalproject

import android.os.Bundle
import android.support.v4.app.Fragment
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.webkit.JavascriptInterface
import kotlinx.android.synthetic.main.graph_fragment.*

class GraphFragment : Fragment() {

    companion object {
        val EQUATION_KEY = "equation key"
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.graph_fragment, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        val equation = arguments?.getString(EQUATION_KEY)
        graphing_view.apply {
            settings.javaScriptEnabled = true
            Log.d("GraphFragment", "Equation: $equation")
            addJavascriptInterface(EquationGetter(equation ?: ""), "equationGetter")
            //addJavascriptInterface(EquationGetter("(t+1,t)"), "equationGetter")
            loadUrl("file:///android_res/raw/desmos_graphing.html")
        }
    }

    //TODO: change to parametric
    class EquationGetter (val equation: String) {
        @JavascriptInterface
        fun getLatex() = equation
    }
}