package com.example.capstone_project.model

import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize
import java.util.*

@Parcelize
@Entity(tableName = "downloadsTable")
data class Download(
    @ColumnInfo(name = "movieTitle")
    var movieTitle: String,

    @ColumnInfo(name = "language")
    @SerializedName("language") // TODO: new data from opensubtitles api
    var language: String,

    @ColumnInfo(name = "files")
    @SerializedName("files") // TODO: new data from opensubtitles api
    var files: List<Files>,

    @ColumnInfo(name = "imgUrl")
    var posterImgUrl: String,

    @ColumnInfo(name = "downloadDate")
    var downloadDate: Calendar,

    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    var id: Long? = null
) : Parcelable
{
    // Function to get backdrop image file name
    fun getCountryFlag() = "https://www.countryflags.io/$language/flat/64.png"
}
