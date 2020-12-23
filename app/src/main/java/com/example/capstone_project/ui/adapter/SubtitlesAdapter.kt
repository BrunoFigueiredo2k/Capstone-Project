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
import com.example.capstone_project.model.Movie
import kotlinx.android.synthetic.main.item_download.view.*

class SubtitlesAdapter(private val downloads: List<Download>, private val onClick: (Download) -> Unit) :
    RecyclerView.Adapter<MovieAdapter.ViewHolder>() {

    private lateinit var context: Context

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DownloadViewHolder {
        context = parent.context

        return DownloadViewHolder(
            LayoutInflater.from(context).inflate(R.layout.item_download, parent, false)
        )
    }

    override fun getItemCount(): Int = downloads.size

    override fun onBindViewHolder(holder: MovieAdapter.ViewHolder, position: Int) = holder.databind(downloads[position], position)

    inner class DownloadViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        init {
            itemView.setOnClickListener { onClick(downloads[adapterPosition]) }
        }
        @SuppressLint("SetTextI18n")
        fun databind(download: Download, position: Int) {
            // TODO bind download data
            itemView.tvMovieTitle.text = ".."
            Glide.with(context).load(download.getCountryFlag()).into(itemView.ivMoviePoster)
        }

    }
}