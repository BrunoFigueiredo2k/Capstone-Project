package com.example.capstone_project.ui.adapter

import android.annotation.SuppressLint
import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import android.widget.Toast.LENGTH_LONG
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.capstone_project.R
import com.example.capstone_project.model.Attributes
import com.example.capstone_project.model.Download
import kotlinx.android.synthetic.main.item_downloaded.view.*
import java.text.SimpleDateFormat
import java.util.*

class DownloadsAdapter(private val downloads: List<Download>) : RecyclerView.Adapter<DownloadsAdapter.ViewHolder>() {
    private lateinit var context: Context

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        context = parent.context

        return ViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.item_downloaded, parent, false)
        )
    }

    override fun getItemCount(): Int {
        return downloads.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int){
        holder.databind(downloads[position])
    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        @SuppressLint("SetTextI18n")
        fun databind(download: Download) {
            Log.d("language", download.attributes.language)
//            Log.d("attributes", download.attributes.javaClass.name)
//            Log.d("attributes", download.attributes.toString())
//            Log.d("filename", download.attributes.files[0].fileName)
//            Log.d("filename", download.attributes.files[0].fileName.javaClass.name)
            Log.d("object", download.toString())
            Log.d("object", download.attributes.toString())

//            Log.d("attributes", download.attributes.relatedLinks.posterImgUrl)
//            Log.d("attributes", download.attributes.featureDetails.movieTitle)
//            Log.d("attributes", printYearMonthDayFormat(download.downloadDate))

            Glide.with(context).load(download.attributes.relatedLinks.posterImgUrl).into(itemView.ivMoviePoster)
            itemView.tvMovieTitle.text = download.attributes.featureDetails.movieTitle
            itemView.tvMovieLanguage.text = download.attributes.language
            itemView.tvMovieFile.text = download.attributes.files[0].fileName
            itemView.tvDownloadDate.text = "downloaded: " + printYearMonthDayFormat(download.downloadDate)
        }
    }

    // Function to format Date type to DD/MM/YYYY
    @SuppressLint("SimpleDateFormat")
    private fun printYearMonthDayFormat(date : Date): String {
        val format = SimpleDateFormat("dd/MM/yyy")
        return format.format(date)
    }
}