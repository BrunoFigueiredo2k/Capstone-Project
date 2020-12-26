package com.example.capstone_project.ui.adapter

import android.annotation.SuppressLint
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.capstone_project.R
import com.example.capstone_project.model.Download
import com.example.capstone_project.ui.adapter.SubtitlesAdapter.*
import kotlinx.android.synthetic.main.item_download.view.*

class SubtitlesAdapter(private val downloads: List<Download>, private val onClick: (Download) -> Unit) :
    RecyclerView.Adapter<SubtitlesAdapter.ViewHolder>() {

    private lateinit var context: Context

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SubtitlesAdapter.ViewHolder {
        context = parent.context

        return ViewHolder (
            LayoutInflater.from(context).inflate(R.layout.item_download, parent, false)
        )
    }

    override fun getItemCount(): Int = downloads.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) = holder.databind(downloads[position])

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        init {
            itemView.setOnClickListener { onClick(downloads[adapterPosition]) }
        }
        @SuppressLint("SetTextI18n")
        fun databind(download: Download) {
            Glide.with(context).load(download.getCountryFlag()).into(itemView.ivMoviePoster)
            itemView.tvMovieLanguage.text = download.language
            itemView.tvMovieFile.text = download.fileName
        }

    }
}