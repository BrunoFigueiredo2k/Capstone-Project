package com.example.capstone_project.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.example.capstone_project.model.Download

@Dao
interface DownloadDao {
    // Get all downloads with newest downloads first
    @Query("SELECT * FROM downloadsTable ORDER BY downloadDate DESC")
    fun getAllDownloads(): LiveData<List<Download>>

    @Insert
    suspend fun insertDownload(download: Download)
}