package com.example.kyros.calcbcfinalproject

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

class VideosRecyclerViewAdapter<T>(val layoutRes : Int, val items: List<T>, private val bind: View.(T) -> Unit): RecyclerView.Adapter<VideosRecyclerViewAdapter.Holder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Holder {
        val inflater = LayoutInflater.from(parent.context)
        return Holder(inflater.inflate(layoutRes, parent, false))
    }

    override fun getItemCount(): Int = items.size

    override fun onBindViewHolder(holder: Holder, pos: Int) {
        holder.itemView.bind(items[pos])
    }

    class Holder(itemView: View): RecyclerView.ViewHolder(itemView)
}