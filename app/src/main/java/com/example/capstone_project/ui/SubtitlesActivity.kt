package com.example.capstone_project.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.capstone_project.R
import com.example.capstone_project.model.Movie
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.activity_subtitles.*

class SubtitlesActivity : AppCompatActivity(){
    // API url for country flags (language)
    val countryFlagBaseUrl : String = "https://www.countryflags.io/"
    val countryFlagExtensionUrl : String = "/flat/64.png"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_subtitles)

        // set toolbar as support action bar
        // TODO: fix setting up toolbar
        setSupportActionBar(findViewById(R.id.toolbar))

        supportActionBar?.apply {
            title = R.string.title_download_subs.toString()
            // show back button on toolbar on back button press, it will navigate to movies activity
            setDisplayHomeAsUpEnabled(true)
            setDisplayShowHomeEnabled(true)
        }

        // Get movie sent with intent from MovieFragment to this activity
        val movie = intent.getParcelableExtra<Movie>("movie")

        // Set movie title from intent
        tvMovieTitle.text = movie?.title

        // Set language flag & text and subtitle file name
        // TODO: fill the dots with the data when subs api call has been fixed
//        Glide.with(this).load(countryFlagBaseUrl + ... + countryFlagExtensionUrl).into(ivMoviePoster)
//        tvMovieFile.text = ...
    }
}