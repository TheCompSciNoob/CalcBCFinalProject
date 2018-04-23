package com.example.kyros.calcbcfinalproject

import android.arch.lifecycle.MutableLiveData
import android.os.Bundle
import android.support.v4.app.Fragment
import android.util.Log
import android.view.LayoutInflater
import android.view.MotionEvent
import android.view.View
import android.view.ViewGroup
import android.widget.VideoView
import com.example.kyros.calcbcfinalproject.databinding.VideoFragmentBinding
import kotlinx.android.synthetic.main.video_fragment.*

class VideoFragment : Fragment() {

    private val pointsLiveData = MutableLiveData<Int>().apply { value = 0 }
    private val pointsList = arrayListOf<ParametricPoint>()
    private var isVideoPlaying = false

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        super.onCreateView(inflater, container, savedInstanceState)
        val binding = VideoFragmentBinding.inflate(inflater, container, false).apply {
            setLifecycleOwner(this@VideoFragment)
            numPointsLiveData = pointsLiveData
            handler = this@VideoFragment
        }
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        //video view
        play_pause_video_view.setOnTouchListener(object : View.OnTouchListener {
            override fun onTouch(v: View?, event: MotionEvent?): Boolean {
                if (event?.action == MotionEvent.ACTION_DOWN) {
                    val t = (v as VideoView).currentPosition
                    val point = ParametricPoint(t.toDouble(), event?.x ?: 0f, v.height - (event?.y ?: 0f))
                    pointsList.add(point)
                    Log.d("VideoFragment", point.toString());
                    pointsLiveData.value = pointsLiveData.value!! + 1
                    return true
                }
                return false
            }
        })
    }

    fun onPlayPauseClick(view: View) {
        if (isVideoPlaying) {
            play_pause_video_view.pause()
        } else {
            play_pause_video_view.pause()
        }
        isVideoPlaying = !isVideoPlaying
    }

    fun generateEquationsButtonClick(view: View) {
        //TODO: start new fragment and generate equations
        Log.d("test", "its working")
    }
}