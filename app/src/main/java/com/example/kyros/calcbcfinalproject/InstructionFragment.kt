package com.example.kyros.calcbcfinalproject

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.os.Environment
import android.provider.MediaStore
import android.support.v4.app.Fragment
import android.support.v7.widget.DividerItemDecoration
import android.support.v7.widget.LinearLayoutManager
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast

import com.example.kyros.calcbcfinalproject.databinding.InstructionFragmentBinding
import kotlinx.android.synthetic.main.instruction_fragment.*
import java.io.File


/**
 * Created by Angela on 2/25/18.
 */


class InstructionFragment : Fragment(), VideosRecyclerViewAdapter.OnRecyclerViewClickListener {

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
            adapter = VideosRecyclerViewAdapter(videoPaths, this@InstructionFragment)
            addItemDecoration(DividerItemDecoration(context, DividerItemDecoration.VERTICAL))
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

    override fun onItemClick(view: View, position: Int) {
        val fragment = VideoFragment().apply {
            videoPath = videoPaths[position]
        }
        fragmentManager!!.beginTransaction()
                .replace(R.id.fragment_container, fragment)
                .addToBackStack(null)
                .commit()
    }

    override fun onDeleteClick(view: View, position: Int) {
        //val file = File("file://${videoPaths[position]}")
        //context?.deleteFile(videoPaths[position].substringAfterLast('/'))
        val file = File(Environment.getExternalStorageDirectory(), videoPaths[position].substringAfterLast('/'))
        if (file.delete()) updateRecyclerView() else Toast.makeText(context, "Delete Failed", Toast.LENGTH_SHORT).show()
    }

    companion object {
        private val REQUEST_VIDEO_CAPTURE = 1234
        private val TAG = "InstructionFragment"
    }
}