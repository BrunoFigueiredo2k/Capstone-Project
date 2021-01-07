package com.example.capstone_project.ui

import android.app.DownloadManager
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.graphics.Color
import android.net.Uri
import android.os.Bundle
import android.os.Environment
import android.util.Log
import android.view.View
import android.widget.TextView
import android.widget.Toast
import android.widget.Toast.LENGTH_LONG
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.capstone_project.R
import com.example.capstone_project.interfaces.SubtitlesApiService
import com.example.capstone_project.model.Download
import com.example.capstone_project.model.Movie
import com.example.capstone_project.ui.ViewModel.DownloadViewModel
import com.example.capstone_project.ui.ViewModel.MovieViewModel
import com.example.capstone_project.ui.ViewModel.SubtitleViewModel
import com.example.capstone_project.ui.adapter.SubtitlesAdapter
import com.example.capstone_project.ui.api.SubtitleApi
import com.google.android.material.snackbar.Snackbar
import kotlinx.android.synthetic.main.activity_subtitles.*
import java.io.File
import java.util.*

class SubtitlesActivity : AppCompatActivity() {
    private val subtitleViewModel: SubtitleViewModel by viewModels()
    private val downloadViewModel: DownloadViewModel by viewModels()
    private val movieViewModel: MovieViewModel by viewModels()
    private val downloads = arrayListOf<Download>()
    private val subtitlesAdapter =
        SubtitlesAdapter(downloads) { download ->
            downloadSubtitle(download)
        }

    // Observe livedata String for imdbId and pass as param to fetch subtitles based on this id
    private fun observeMovieImdbId() {
        movieViewModel.movieId.observe(this, Observer { id ->
            fetchSubtitles(id)
        })
    }

    private fun observeSubtitles() {
        subtitleViewModel.downloads.observe(this, Observer { download ->
            downloads.clear()
            downloads.addAll(download)
            subtitlesAdapter.notifyDataSetChanged()
        })
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_subtitles)

        setUpActionBar()

        // Get movie sent with intent from MovieFragment to this activity
        val movie = intent.getParcelableExtra<Movie>("movie")
        // Set movie title from intent
        tvMovieTitle.text = movie?.title

        // Get imdb_id to pass to opensubtitles api
        val movieId = movie?.id
        movieViewModel.getImdbId(movieId.toString())

        initViews()

        observeMovieImdbId()
        observeSubtitles()
    }

    // Fetch subtitles for passed movie based on imdb_id
    private fun fetchSubtitles(imdbId: String) {
        subtitleViewModel.fetchSubtitles(imdbId)
    }

    fun initViews() {
        rvSubtitles.layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
        rvSubtitles.addItemDecoration(DividerItemDecoration(this, DividerItemDecoration.VERTICAL))
        rvSubtitles.adapter = subtitlesAdapter
    }

    // Click listener for specific subtitle of movie
    private fun downloadSubtitle(download: Download) {
        val movieTitle = download.attributes.featureDetails.movieTitle
        val fileName = download.attributes.files[0].fileName
//        var downloadId : Long = 0
//
//        // Getting the download url from API
//        val subtitlesApiService: SubtitlesApiService = SubtitleApi.createApi()
//        val downloadUrl = suspend {
//            subtitlesApiService.fetchDownloadUrl().downloadUrl
//        }
//
//        Log.d("downloadUrl", downloadUrl.toString())
//
//        val request = DownloadManager.Request(
//            //TODO: add download url here by calling fetchDownloadUrl()
//            Uri.parse(downloadUrl.toString()))
//            .setTitle(movieTitle)
//            .setDescription("Downloading $fileName ($movieTitle)")
//            .setNotificationVisibility(DownloadManager.Request.VISIBILITY_VISIBLE_NOTIFY_ONLY_COMPLETION)
//            .setDestinationInExternalPublicDir(Environment.DIRECTORY_DOWNLOADS, fileName);
//
//        val dm = getSystemService(Context.DOWNLOAD_SERVICE) as DownloadManager
//        downloadId = dm.enqueue(request)
//
//        // Checks if download is complete
//        val broadcast = object:BroadcastReceiver(){
//            override fun onReceive(p0: Context?, p1: Intent?) {
//                val id = p1?.getLongExtra(DownloadManager.EXTRA_DOWNLOAD_ID, -1)
//                // If the request id and downloadId set above are equal then file is downloaded
//                if (id == downloadId) {
//                    // Show message to user in Snackbar
//                    val parentLayout = findViewById<View>(android.R.id.content)
//                    showSnackbarDownloaded(fileName, movieTitle, parentLayout)
//                    // Insert download into db
//                    downloadViewModel.insertDownload(download)
//                }
//            }
//        }
//
//        // When download manager action is completed then look through broadcast variable
//        registerReceiver(broadcast, IntentFilter(DownloadManager.ACTION_DOWNLOAD_COMPLETE))

        downloadViewModel.insertDownload(download)
        val parentLayout = findViewById<View>(android.R.id.content)
        showSnackbarDownloaded(fileName, movieTitle, parentLayout)
    }

    // Setting up action bar with back arrow
    private fun setUpActionBar() {
        supportActionBar?.apply {
            // show back button on toolbar on back button press, it will navigate to home activity
            setDisplayHomeAsUpEnabled(true)
            setDisplayShowHomeEnabled(true)
        }
    }

    // Snackbar function to display snackbar telling user which file of what movie was downloaded
    private fun showSnackbarDownloaded(fileName : String, movieTitle : String, view: View) {
        // Initialize snackbar message
        val snackBar = Snackbar.make(
            view, "Downloaded $fileName ($movieTitle)",
            Snackbar.LENGTH_LONG
        ).setAction("Action", null)

        // Set styling
        snackBar.setActionTextColor(Color.WHITE)
        val snackBarView = snackBar.view
        val textView =
            snackBarView.findViewById(com.google.android.material.R.id.snackbar_text) as TextView
        textView.setTextColor(Color.WHITE)
        snackBar.show()
    }
}