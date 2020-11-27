package com.example.capstone_project.repository

import android.content.Context
import androidx.lifecycle.LiveData
import com.example.capstone_project.dao.DownloadDao
import com.example.capstone_project.database.DownloadsRoomDatabase
import com.example.capstone_project.model.Download

class DownloadRepository (context: Context) {

    private var downloadDao: DownloadDao

    init {
        val DownloadsRoomDatabase = DownloadsRoomDatabase.getDatabase(context)
        downloadDao = DownloadsRoomDatabase!!.downloadDao()
    }

    fun getAllDownloads() : LiveData<List<Download>> {
        return downloadDao?.getAllDownloads()
    }

    suspend fun insertDownload(download: Download) {
        downloadDao.insertDownload(download)
    }
}