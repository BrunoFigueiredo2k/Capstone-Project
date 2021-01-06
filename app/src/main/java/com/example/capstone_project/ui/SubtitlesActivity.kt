package com.example.capstone_project.ui

import android.app.DownloadManager
import android.content.Context
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
import com.example.capstone_project.model.Download
import com.example.capstone_project.model.Movie
import com.example.capstone_project.ui.ViewModel.DownloadViewModel
import com.example.capstone_project.ui.ViewModel.MovieViewModel
import com.example.capstone_project.ui.ViewModel.SubtitleViewModel
import com.example.capstone_project.ui.adapter.SubtitlesAdapter
import com.google.android.material.snackbar.Snackbar
import kotlinx.android.synthetic.main.activity_subtitles.*
import java.io.File

class SubtitlesActivity : AppCompatActivity() {
    private val subtitleViewModel: SubtitleViewModel by viewModels()
    private val downloadViewModel: DownloadViewModel by viewModels()
    private val movieViewModel: MovieViewModel by viewModels()
    private val downloads = arrayListOf<Download>()
    private val subtitlesAdapter =
        SubtitlesAdapter(downloads) { download ->
            // TODO: download is null because this is the download from db instead of download from recyclerview list
            Toast.makeText(this, "Clicked: ${download.id}", LENGTH_LONG).show()
            Log.d("clickicon", "Clicked: $download")

            // TODO: uncomment when function is fully working
            onSubtitleDownloadClick(download)
        }

//    private fun callClickListener(download: Download){
//        subtitlesAdapter.registerAdapterDataObserver(object : RecyclerView.AdapterDataObserver(){
//            override fun onChanged() {
//                Toast.makeText(applicationContext, "Clicked: ${download.id}", LENGTH_LONG).show()
//                Log.d("clickicon", "Clicked: $download")
//            }
//        })
//    }

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
    // TODO: Source: https://stackoverflow.com/questions/63099515/how-to-download-file-from-url-in-android
    private fun onSubtitleDownloadClick(download: Download) {
        // TODO: not sure if writing to this directory is a good idea (Ask Pim?)
        // Directory that the download will be written to
        val directory = File(Environment.DIRECTORY_DOWNLOADS)

        // Check if directory doesn't exist and if it doesn't create directory
        if (!directory.exists()) {
            directory.mkdirs()
        }

        // TODO: downloadmanager accepts the download url, need to figure out what this url is in endpoint
        val downloadFileUrl = download.attributes.files[0].fileName

        // TODO: filename is null check subtitlesadapter error
//        startFileDownload(downloadFileUrl[0].fileName, download)
        startFileDownload(download)
    }

    private fun startFileDownload(download: Download) {
//        val extension = url?.substring(url.lastIndexOf("."))
//        val downloadReference: Long
//        val downloadManager: DownloadManager = this.getSystemService(Context.DOWNLOAD_SERVICE) as DownloadManager
//        val uri = Uri.parse(url)
//        val request = DownloadManager.Request(uri)
//
//        request.setDestinationInExternalPublicDir(
//            "/", // TODO: not sure about this param
//            "srt" + System.currentTimeMillis() + extension
//        )
//            .setNotificationVisibility(DownloadManager.Request.VISIBILITY_VISIBLE_NOTIFY_COMPLETED)
//            .setTitle(title)
//
//        Toast.makeText(this, "start Downloading..", Toast.LENGTH_SHORT).show()
//
//        downloadReference = downloadManager.enqueue(request) ?: 0
//
//        // TODO: check if this is correct place to call snackbar function
        val parentLayout = findViewById<View>(android.R.id.content)
        showSnackbarDownloaded(download, parentLayout)

        // Insert download into db
        downloadViewModel.insertDownload(download)
    }

    // Setting up action bar with back arrow
    private fun setUpActionBar() {
        supportActionBar?.apply {
            // show back button on toolbar on back button press, it will navigate to movies activity
            setDisplayHomeAsUpEnabled(true)
            setDisplayShowHomeEnabled(true)
        }
    }

    // Snackbar function to display snackbar telling user which file of what movie was downloaded
    //TODO: value of download parameters are from db instead of recyclerview (FIX THIS)
    private fun showSnackbarDownloaded(download : Download, view: View) {
        val fileName = download.attributes.files[0].fileName
        val movieTitle = download.attributes.featureDetails.movieTitle

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