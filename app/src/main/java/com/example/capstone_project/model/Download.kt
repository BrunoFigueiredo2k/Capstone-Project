package com.example.capstone_project.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.*

@Entity(tableName = "downloadsTable")
data class Download(
    @ColumnInfo(name = "movieTitle")
    var movieTitle: String,

    @ColumnInfo(name = "language")
    var language: String,

    @ColumnInfo(name = "fileName")
    var fileName: String,

    @ColumnInfo(name = "downloadDate")
    var downloadDate: Date,

    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    var id: Long? = null
)
