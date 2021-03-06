package com.example.capstone_project.ui.ViewModel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import com.example.capstone_project.model.Download
import com.example.capstone_project.repository.DownloadRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.util.*

class DownloadViewModel (application: Application): AndroidViewModel(application) {
    private val ioScope = CoroutineScope(Dispatchers.IO)
    private val downloadRepository = DownloadRepository(application.applicationContext)

    val downloads: LiveData<List<Download>> = downloadRepository.getAllDownloads()

    fun insertDownload(download: Download) {
        ioScope.launch {
            download.downloadDate = Date()
            downloadRepository.insertDownload(download)
        }
    }
}