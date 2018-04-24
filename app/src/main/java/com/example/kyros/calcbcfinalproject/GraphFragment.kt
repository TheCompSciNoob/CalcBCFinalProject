package com.example.kyros.calcbcfinalproject

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.webkit.JavascriptInterface
import kotlinx.android.synthetic.main.graph_fragment.*

class GraphFragment : Fragment() {

    lateinit var testEquation: String

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.graph_fragment, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        graphing_view.apply {
            settings.javaScriptEnabled = true
            addJavascriptInterface(EquationGetter("y=x^2"), "equationGetter")
            loadUrl("file:///android_res/raw/desmos_graphing.html")
        }
    }

    //TODO: change to parametric
    class EquationGetter (val equation: String) {
        @JavascriptInterface
        fun getLatex() = equation
    }
}