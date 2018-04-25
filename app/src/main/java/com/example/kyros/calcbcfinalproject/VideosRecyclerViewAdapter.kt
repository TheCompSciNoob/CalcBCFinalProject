package com.example.kyros.calcbcfinalproject

import android.media.ThumbnailUtils
import android.provider.MediaStore
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.kyros.calcbcfinalproject.databinding.SavedVideoItemBinding
import kotlinx.android.synthetic.main.saved_video_item.view.*

class VideosRecyclerViewAdapter(val videoPaths: List<String>, val itemListener: OnRecyclerViewClickListener): RecyclerView.Adapter<VideosRecyclerViewAdapter.Holder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Holder {
        val inflater = LayoutInflater.from(parent.context)
        return Holder(SavedVideoItemBinding.inflate(inflater, parent, false))
    }

    override fun getItemCount(): Int = videoPaths.size

    override fun onBindViewHolder(holder: Holder, pos: Int) = with(holder.binding) {
        listener = itemListener
        filename = "file://${videoPaths[pos]}"
        position = pos
        executePendingBindings()
        val thumbnail = ThumbnailUtils.createVideoThumbnail("file://$filename", MediaStore.Video.Thumbnails.MINI_KIND)
        root.imageView.setImageBitmap(thumbnail)
    }

    class Holder(val binding: SavedVideoItemBinding): RecyclerView.ViewHolder(binding.root) {}

    interface OnRecyclerViewClickListener {

        fun onItemClick(view: View, position: Int)

        fun onDeleteClick(view: View, position: Int)
    }
}