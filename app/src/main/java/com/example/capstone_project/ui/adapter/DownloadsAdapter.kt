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
import kotlinx.android.synthetic.main.item_downloaded.view.*
import java.util.*

class DownloadsAdapter(private val games: List<Download>) : RecyclerView.Adapter<DownloadsAdapter.ViewHolder>() {
    private lateinit var context: Context

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        context = parent.context

        return ViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.item_downloaded, parent, false)
        )
    }

    /** Returns the size of the list */
    override fun getItemCount(): Int {
        return games.size
    }

    /** Called by RecyclerView to display the data at the specified position. */
    override fun onBindViewHolder(holder: ViewHolder, position: Int){
        holder.databind(games[position])
    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        @SuppressLint("SetTextI18n")
        fun databind(download: Download) {
            Glide.with(context).load(download.relatedLinks.posterImgUrl).into(itemView.ivMoviePoster)
            itemView.tvMovieTitle.text = download.featureDetails.movieTitle
            itemView.tvMovieLanguage.text = download.attributes.language
            itemView.tvMovieFile.text = download.files[0].fileName // TODO: check if this really gets the filename
            itemView.tvDownloadDate.text = "downloaded: " + printYearMonthDayFormat(download.downloadDate)
        }
    }

    // Function to format Date type to DD/MM/YYYY
    private fun printYearMonthDayFormat(date : Calendar): String {
        val year: Int = date.get(Calendar.YEAR)
        val month: Int = date.get(Calendar.MONTH)
        val day: Int = date.get(Calendar.DAY_OF_WEEK)

        return "$day/$month/$year"
    }
}