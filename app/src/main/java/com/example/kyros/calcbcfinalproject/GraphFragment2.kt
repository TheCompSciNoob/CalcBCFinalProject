package com.example.kyros.calcbcfinalproject

import android.os.Bundle
import android.support.v4.app.Fragment
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import kotlinx.android.synthetic.main.abc_alert_dialog_button_bar_material.*
import kotlinx.android.synthetic.main.graph_fragment_2.*

class GraphFragment2: Fragment() {

    companion object {
        const val RAW_POINTS_KEY = "raw points key"
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.graph_fragment_2, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        Log.d("GraphFragment2", "onViewCreated()")

        //graphing
        val rawPoints = arguments?.getParcelableArrayList<ParametricPoint>(RAW_POINTS_KEY)?.sortedBy { it.t } ?: throw IllegalStateException("no points retreived from arguments")
        val fitterX = PolynomialFitter(2)
        val fitterY = PolynomialFitter(2)
        rawPoints.forEach {
            fitterX.addPoint(it.t, it.x.toDouble())
            fitterY.addPoint(it.t, it.y.toDouble())
        }
        val bfX = fitterX.bestFit
        val bfY = fitterY.bestFit
        val util = PhysicsUtil(bfX[2], bfX[1], bfX[0], bfY[2], bfY[1], bfY[0])
        val plotPoints = util.createPlotPoints(10, rawPoints.first().t, rawPoints.last().t - rawPoints.first().t)
        graphing_view.points = plotPoints

        //calculations
        calculate_1.setOnClickListener {
            val time = time_1.text.toString().toDouble()
            velocity_acceleration.text = "Velocity: ${util.getVelocity(time)}\nAcceleration: ${util.getAcceleration(time)}"
        }
        calculate_2.setOnClickListener {
            val ti = initial_time.text.toString().toDouble() //TODO: LOL didn't use
            val tf = final_time.text.toString().toDouble()
            distance_travelled.text = "Distance Travelled: ${util.getArcLength(tf)}"
        }
        calculate_3.setOnClickListener {
            val mass = mass.text.toString().toDouble()
            val time = time_2.text.toString().toDouble()
            force.text = "Force: ${mass * util.getAcceleration(time)}"
        }
    }
}