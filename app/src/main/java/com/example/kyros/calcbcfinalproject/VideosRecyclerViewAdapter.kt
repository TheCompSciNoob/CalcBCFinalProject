package com.example.kyros.calcbcfinalproject

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.kyros.calcbcfinalproject.databinding.SavedVideoItemBinding

class VideosRecyclerViewAdapter(val videoInfos: List<VideoInfo>, val itemListener: OnRecyclerViewClickListener): RecyclerView.Adapter<VideosRecyclerViewAdapter.Holder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Holder {
        val inflater = LayoutInflater.from(parent.context)
        return Holder(SavedVideoItemBinding.inflate(inflater, parent, false))
    }

    override fun getItemCount(): Int = videoInfos.size

    override fun onBindViewHolder(holder: Holder, pos: Int) = with(holder.binding) {
        listener = itemListener
        videoInfo = videoInfos[pos]
        position = pos
        executePendingBindings()
    }

    data class VideoInfo(val filename: String)

    class Holder(val binding: SavedVideoItemBinding): RecyclerView.ViewHolder(binding.root) {}

    interface OnRecyclerViewClickListener {

        fun onItemClick(view: View, position: Int)

        fun onDeleteClick(view: View, position: Int)
    }
}