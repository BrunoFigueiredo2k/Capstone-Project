package com.example.capstone_project.ui.adapter

import android.annotation.SuppressLint
import android.content.Context
import android.graphics.drawable.Drawable
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.target.CustomTarget
import com.example.capstone_project.R
import com.example.capstone_project.model.Download
import com.example.capstone_project.model.Language
import com.example.capstone_project.model.Languages
import com.google.gson.Gson
import kotlinx.android.synthetic.main.item_download.view.*
import org.json.JSONObject
import java.io.FileReader


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
            // Set click listener only for downloadIcon
            itemView.downloadIcon.setOnClickListener { onClick(downloads[adapterPosition]) }
        }
        @SuppressLint("SetTextI18n")
        fun databind(download: Download) {
            // Pass flag that's been returned through the determineLanguage flag converter. This is
            // done to convert to language value that's returned from the api to the corresponding country
            Glide.with(context).load(getCountryFlag(determineLanguageFlag(download.attributes.language))).into(itemView.ivMoviePoster)
            itemView.tvMovieLanguage.text = download.attributes.language

            // TODO: put this in itemView tvMovieLanguage (return value will be langauge name
            getLanguageNameJson(download.attributes.language)

//            itemView.tvMovieFile.text = download.files[0].fileName // TODO: fix setting the filename from the list. error: nullpointer
        }
    }

    private fun getLanguageNameJson(languageCode : String) : String{
        val gson = Gson()
        val jsonfile: String = context.assets.open("languages.json").bufferedReader().use {it.readText()}
        val json = JSONObject(jsonfile).toString()
        Log.d("json", json)

        val languages = gson.fromJson(json, Languages::class.java)
        val code = languages.languages[0].code
//        val name = languages.languages[0].name
        val lengthArray : Int = languages.languages.size

        // TODO: fix this
        for (i in languages.languages.indices){
            val nameLanguage = languages.languages[i].name
            if (languages.languages[i].code === languageCode){
                Log.d("languageName", nameLanguage)
            }
        }
//        Log.d("json", "$code - $name")
        Log.d("json", lengthArray.toString())

        // TODO: return languages object
        return ""
    }

    // Get country flag from flags api url
    private fun getCountryFlag(language : String) : String{
        return "https://www.countryflags.io/$language/flat/64.png"
    }

    // Determine the flag/country of the language that's returned from the api
    private fun determineLanguageFlag(language : String) : String{
        var returnLanguage = language
        when (language){
            "en" -> returnLanguage = "gb"
            "pt-BR" -> returnLanguage = "br"
            "pt-PT" -> returnLanguage = "pt"
            "ko" -> returnLanguage = "kr"
            "ja" -> returnLanguage = "jp"
        }
        return returnLanguage
    }
}