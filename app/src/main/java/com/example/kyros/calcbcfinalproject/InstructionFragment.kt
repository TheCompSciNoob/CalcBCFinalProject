package com.example.kyros.calcbcfinalproject

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.provider.MediaStore
import android.support.v4.app.Fragment
import android.support.v7.widget.DividerItemDecoration
import android.support.v7.widget.LinearLayoutManager
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.bumptech.glide.Glide

import com.example.kyros.calcbcfinalproject.databinding.InstructionFragmentBinding
import kotlinx.android.synthetic.main.instruction_fragment.*
import kotlinx.android.synthetic.main.saved_video_item.view.*


/**
 * Created by Angela on 2/25/18.
 */


class InstructionFragment : Fragment() {

    private val videoPaths = arrayListOf<String>()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val binding = InstructionFragmentBinding.inflate(inflater, container, false)
        binding.fragment = this
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        //recyclerview
        videos_recycler_view.apply {
            layoutManager = LinearLayoutManager(context)
            addItemDecoration(DividerItemDecoration(context, DividerItemDecoration.VERTICAL))
            //adapter = VideosRecyclerViewAdapter(videoPaths, this@InstructionFragment)
            adapter = VideosRecyclerViewAdapter(R.layout.saved_video_item, videoPaths) { path ->
                Glide.with(this).load(path).into(video_thumbnail)
                video_file_name.text = path
                view_item_button.setOnClickListener {
                    val args = Bundle().apply {
                        putString(VideoFragment.VIDEO_PATH_KEY, path)
                    }
                    val fragment = VideoFragment().apply {
                        arguments = args
                    }
                    fragmentManager!!.beginTransaction()
                            .replace(R.id.fragment_container, fragment)
                            .addToBackStack(null)
                            .commit()
                }
                delete_item_button.setOnClickListener {
                    //TODO: add delete functionality
                }
            }
        }
        updateRecyclerView()
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == REQUEST_VIDEO_CAPTURE && resultCode == Activity.RESULT_OK) {
            //TODO: save to directory using getPublicVideoStorageDir()
            updateRecyclerView()
        }
    }

    private fun updateRecyclerView() {
        val uri = MediaStore.Video.Media.EXTERNAL_CONTENT_URI
        val projection = arrayOf(MediaStore.Video.VideoColumns.DATA)
        val c = context?.contentResolver?.query(uri, projection, null, null, null)
        c?.let {
            videoPaths.clear()
            while (it.moveToNext()) {
                //TODO: fix the video path
                videoPaths.add(it.getString(0))
                Log.d(TAG, it.getString(0))
            }
            videos_recycler_view.adapter.notifyDataSetChanged()
        }
        c?.close()
    }

    fun takeVideo(view: View) {
        val takeVideoIntent = Intent(MediaStore.ACTION_VIDEO_CAPTURE)
        if (takeVideoIntent.resolveActivity(context?.packageManager) != null) {
            startActivityForResult(takeVideoIntent, REQUEST_VIDEO_CAPTURE)
        }
    }

    companion object {
        private val REQUEST_VIDEO_CAPTURE = 1234
        private val TAG = "InstructionFragment"
    }
}