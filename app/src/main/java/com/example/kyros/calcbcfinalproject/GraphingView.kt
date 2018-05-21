package com.example.kyros.calcbcfinalproject

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.util.AttributeSet
import android.util.Log
import android.view.View
import kotlin.math.max

class GraphingView(context: Context?, attributeSet: AttributeSet?) : View(context, attributeSet) {

    private var lowerX: Float = 0f
    private var lowerY: Float = 0f
    private var size: Float = 1000f
    var points: List<ParametricPoint> = emptyList()
        set(value) {
            field = value
            val minX = value.minBy { it.x }!!.x
            val maxX = value.maxBy { it.x }!!.x
            val minY = value.minBy { it.y }!!.y
            val maxY = value.minBy { it.y }!!.y
            lowerX = minX
            lowerY = minY
            size = max(maxX - minX, maxY - minY)
        }
    private val floatList: MutableList<Float> = mutableListOf()

    //graphics
    private val paint = Paint(Color.BLACK)

    override fun onDraw(canvas: Canvas?) {
        super.onDraw(canvas)
        floatList.clear()
        points.forEach {
            floatList.add((it.x - lowerX) / size * width)
            floatList.add(height - (it.y - lowerY) / size * height)
        }
        canvas?.drawLines(floatList.toFloatArray(), paint)
        Log.d("GraphingView", "lines drawn")
    }
}