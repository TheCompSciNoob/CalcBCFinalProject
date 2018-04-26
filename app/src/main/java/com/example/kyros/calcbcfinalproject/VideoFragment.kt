package com.example.kyros.calcbcfinalproject

import android.arch.lifecycle.MutableLiveData
import android.os.Bundle
import android.support.v4.app.Fragment
import android.util.Log
import android.view.LayoutInflater
import android.view.MotionEvent
import android.view.View
import android.view.ViewGroup
import android.widget.MediaController
import android.widget.VideoView
import com.example.kyros.calcbcfinalproject.databinding.VideoFragmentBinding
import kotlinx.android.synthetic.main.video_fragment.*

class VideoFragment : Fragment() {

    private val pointsLiveData = MutableLiveData<Int>().apply { value = 0 }
    private val pointsList = arrayListOf<ParametricPoint>()
    private var isVideoPlaying = false

    //video path
    lateinit var videoPath : String

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
        play_pause_video_view.apply {
            setMediaController(MediaController(context))
            setOnTouchListener(object : View.OnTouchListener {
                override fun onTouch(v: View?, event: MotionEvent?): Boolean {
                    if (event?.action == MotionEvent.ACTION_DOWN) {
                        val t = (v as VideoView).currentPosition
                        val point = ParametricPoint(t.toDouble(), event.x, v.height - event.y)
                        pointsList.add(point)
                        coordinates_text_view.append("$point\n")
                        Log.d("VideoFragment", point.toString());
                        pointsLiveData.value = pointsLiveData.value!! + 1
                    }
                    return true
                }
            })
            setVideoPath("file://$videoPath")
            seekTo(0)
            pause()
        }
    }

    fun onPlayPauseClick(view: View) {
        if (isVideoPlaying) {
            play_pause_video_view.pause()
            Log.d("onPlayPauseClick: ", "pause")
        } else {
            play_pause_video_view.start()
            Log.d("onPlayPauseClick: ", "play")
        }
        isVideoPlaying = !isVideoPlaying
    }

    fun generateEquationsButtonClick(view: View) {
        //Regression
        /*val xCoeffs = PolynomialRegression.polyRegression(pointsList.map { it.t.roundToInt() }.toIntArray(), pointsList.map { it.x.roundToInt() }.toIntArray())
        val yCoeffs = PolynomialRegression.polyRegression(pointsList.map { it.t.roundToInt() }.toIntArray(), pointsList.map { it.y.roundToInt() }.toIntArray())
        //VideoFragment
        val bundle = Bundle().apply {
            putDouble(GraphFragment.X_A, xCoeffs[0])
            putDouble(GraphFragment.X_B, xCoeffs[1])
            putDouble(GraphFragment.X_C, xCoeffs[2])
            putDouble(GraphFragment.Y_A, yCoeffs[0])
            putDouble(GraphFragment.Y_B, yCoeffs[1])
            putDouble(GraphFragment.Y_C, yCoeffs[2])
        }
        val fragment = GraphFragment().apply {
            arguments = bundle
        }*/
        val bundle = Bundle().apply {
            putParcelableArrayList(GraphFragment.PARAMETRIC_POINTS_KEY, pointsList)
        }
        val fragment = GraphFragment().apply { arguments = bundle }
        //pop old fragment and replace
        fragmentManager?.apply {
            popBackStack()
            beginTransaction()
                    .replace(R.id.fragment_container, fragment)
                    .addToBackStack(null)
                    .commit()
        }
    }
}