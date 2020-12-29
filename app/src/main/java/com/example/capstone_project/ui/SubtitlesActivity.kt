package com.example.capstone_project.ui

import android.app.DownloadManager
import android.content.Context
import android.content.Intent
import android.database.Cursor
import android.net.Uri
import android.os.Bundle
import android.os.Environment
import android.widget.Toast
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
import java.io.File

class SubtitlesActivity : AppCompatActivity(){
    private val downloads = arrayListOf<Download>()
    private val viewModel: SubtitleViewModel by viewModels()
    private val subtitlesAdapter =
        SubtitlesAdapter(downloads) { download ->
            onSubtitleDownloadClick(download)
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
        var tmdbId = movie?.getMovieImdbId()
        if (tmdbId != null) {
            fetchSubtitles(tmdbId)
        }

        initViews()
    }

    // Fetch subtitles for passed movie based on imdb_id
    private fun fetchSubtitles(imdbId : String){
        viewModel.fetchSubtitles(imdbId)
    }

    fun initViews(){
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
        val downloadFileUrl = download.fileName

        startFileDownload(downloadFileUrl)
    }

    private fun startFileDownload(url : String): Long{
        val extension = url?.substring(url.lastIndexOf("."))
        val downloadReference: Long
        var downloadManager: DownloadManager
        downloadManager = this.getSystemService(Context.DOWNLOAD_SERVICE) as DownloadManager
        val uri = Uri.parse(url)
        val request = DownloadManager.Request(uri)
        request.setDestinationInExternalPublicDir(
            "/your_folder",
            "srt" + System.currentTimeMillis() + extension
        )
        request.setNotificationVisibility(DownloadManager.Request.VISIBILITY_VISIBLE_NOTIFY_COMPLETED)
        request.setTitle(title)
        Toast.makeText(this, "start Downloading..", Toast.LENGTH_SHORT).show()

        downloadReference = downloadManager?.enqueue(request) ?: 0

        return downloadReference
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