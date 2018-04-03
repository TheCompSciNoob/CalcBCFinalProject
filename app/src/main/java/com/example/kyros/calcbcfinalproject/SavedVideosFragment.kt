package com.example.kyros.calcbcfinalproject

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

/**
 * Created by Angela on 4/3/18.
 */
class SavedVideosFragment : Fragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
/*        val binding = SavedVideosFragmentBinding.inflate(inflater, container, false)
        //TODO: binding.setVariable()
        //TODO: recyclerview
        return binding.root*/

        return inflater.inflate(R.layout.saved_videos_fragment, container, false)
    }
}