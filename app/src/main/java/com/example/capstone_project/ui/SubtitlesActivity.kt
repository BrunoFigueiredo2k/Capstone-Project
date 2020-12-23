package com.example.capstone_project.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import com.example.capstone_project.R
import com.example.capstone_project.model.Movie

class SubtitlesActivity : AppCompatActivity(){
    val imageUrl : String = "https://image.tmdb.org/t/p/w500/"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.subtitles_activity)

        // Get movie sent with intent from MovieFragment to this activity
        val movie = intent.getParcelableExtra<Movie>("movie")

        // Set images using glide and text of specific movie
//        Glide.with(this).load(imageUrl + movie?.backdropImg).into(ivBackdropImg)
//        Glide.with(this).load(imageUrl + movie?.posterImg).into(ivPosterImg)
//        tvTitle.text = movie?.title
//        tvReleaseDate.text = movie?.releaseDate
//        tvRating.text = movie?.rating.toString()
//        tvOverview.text = movie?.overview
    }
}