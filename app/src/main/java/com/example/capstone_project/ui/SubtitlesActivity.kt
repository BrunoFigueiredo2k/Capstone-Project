package com.example.capstone_project.ui

import android.content.Intent
import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.capstone_project.R
import com.example.capstone_project.model.Download
import com.example.capstone_project.model.Movie
import com.example.capstone_project.ui.ViewModel.MovieViewModel
import com.example.capstone_project.ui.ViewModel.SubtitleViewModel
import com.example.capstone_project.ui.adapter.DownloadsAdapter
import com.example.capstone_project.ui.adapter.MovieAdapter
import com.example.capstone_project.ui.adapter.SubtitlesAdapter
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.activity_subtitles.*
import kotlinx.android.synthetic.main.fragment_movies.*
import kotlinx.android.synthetic.main.item_download.*

class SubtitlesActivity : AppCompatActivity(){
    private val downloads = arrayListOf<Download>()
    private val viewModel: SubtitleViewModel by viewModels()
    private val subtitlesAdapter =
        SubtitlesAdapter(downloads) { download ->
            onSubtitleDownloadClick(download)
        }

    // API url for country flags (language)
    val countryFlagBaseUrl : String = "https://www.countryflags.io/"
    val countryFlagExtensionUrl : String = "/flat/64.png"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_subtitles)

        setUpActionBar()

        // TODO: send imdb_id through intent so it can be used as param for subtitles api call
        // Get movie sent with intent from MovieFragment to this activity
        val movie = intent.getParcelableExtra<Movie>("movie")
        // Set movie title from intent
        tvMovieTitle.text = movie?.title

        // Set language flag & text and subtitle file name
        // TODO: fill the dots with the data when subs api call has been fixed
//        Glide.with(this).load(countryFlagBaseUrl + ... + countryFlagExtensionUrl).into(ivMoviePoster)
//        tvMovieFile.text = ...

        initViews()
    }

    // TODO: pass imdb_id as param here when sending from tmdb api call through intent has been fixed
    private fun fetchSubtitles(){
//        viewModel.fetchSubtitles(...)
    }

    fun initViews(){
        rvSubtitles.layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
        rvSubtitles.addItemDecoration(DividerItemDecoration(this, DividerItemDecoration.VERTICAL))
        rvSubtitles.adapter = subtitlesAdapter
    }

    // TODO: onclick download button fire this download function using DownloadManager
    // Click listener for specific movie
    private fun onSubtitleDownloadClick(download: Download) {

    }

    private fun setUpActionBar() {
        // set toolbar as support action bar
        // TODO: fix setting up toolbar
        setSupportActionBar(findViewById(R.id.toolbar))

        supportActionBar?.apply {
            title = R.string.title_download_subs.toString()
            // show back button on toolbar on back button press, it will navigate to movies activity
            setDisplayHomeAsUpEnabled(true)
            setDisplayShowHomeEnabled(true)
        }
    }

}