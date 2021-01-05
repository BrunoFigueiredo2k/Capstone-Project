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
import com.example.capstone_project.interfaces.GetJson
import com.example.capstone_project.model.Countries
import com.example.capstone_project.model.Download
import com.example.capstone_project.model.Language
import com.example.capstone_project.model.Languages
import com.google.gson.Gson
import kotlinx.android.synthetic.main.item_download.view.*
import org.json.JSONObject
import java.io.FileReader


class SubtitlesAdapter(private val downloads: List<Download>, private val onClick: (Download) -> Unit) :
    RecyclerView.Adapter<SubtitlesAdapter.ViewHolder>(), GetJson {

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
            Glide.with(context).load(getCountryFlag(convertLanguageToCountryCode(download.attributes.language))).into(itemView.ivMoviePoster)

            // Gets the language name instead of code and sets it to the textview
            itemView.tvMovieLanguage.text = getLanguageNameJson(download.attributes.language)

            // TODO: fix setting the filename from the list. error: nullpointer
//            itemView.tvMovieFile.text = download.files[0].fileName
        }
    }

    val gson = Gson()

    // Function to convert language code into language name from languages.json file in assets folder
    private fun getLanguageNameJson(languageCode : String) : String {
        val json = getJson("languages.json", context)

        // Convert json string of file to Model (Languages)
        val languages = gson.fromJson(json, Languages::class.java)

        // Loop through all languages and whenever iteration language code matches the code passed
        // from Download class then return the language name
        var languageName = ""
        for (i in languages.languages.indices){
            if (languageCode == languages.languages[i].code){
                languageName = languages.languages[i].name
            }
        }
        return languageName
    }

    // Get country flag from flags api url
    private fun getCountryFlag(language : String) : String{
        return "https://www.countryflags.io/$language/flat/64.png"
    }

    // Determine the flag/country of the language that's returned from the api
    private fun convertLanguageToCountryCode(language : String) : String{
        // Get countries from json file and convert from json to model
        val jsonCountries = getJson("countries.json", context)
        val countriesObj = gson.fromJson(jsonCountries, Countries::class.java)

        var returnCountryCode = language
        // Loop through countries object with language and country code and if the passed language
        // is equal to the language then pass the country code of that index
        for (i in countriesObj.countries.indices) {
            when (language) {
                // First check the main countries, because there are many countries that speak
                // these languages but should be the original country's flag
                "en" -> returnCountryCode = "gb"
                "pt-BR" -> returnCountryCode = "br"
                "pt-PT" -> returnCountryCode = "pt"
                "fr" -> returnCountryCode = "fr"
                "es" -> returnCountryCode = "es"
                "de" -> returnCountryCode = "de"
                "sr" -> returnCountryCode = "rs"
                "ms" -> returnCountryCode = "my"
                "he" -> returnCountryCode = "il"
                "sl" -> returnCountryCode = "si"
                "bs" -> returnCountryCode = "ba"
                "ar" -> returnCountryCode = "sa"
                "da" -> returnCountryCode = "dk"
                "cs" -> returnCountryCode = "cz"
                // Check all others
                countriesObj.countries[i].languageCode -> returnCountryCode = countriesObj.countries[i].countryCode
            }
        }

        return returnCountryCode
    }
}