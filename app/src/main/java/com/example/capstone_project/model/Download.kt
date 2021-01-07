package com.example.capstone_project.model

import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize
import kotlinx.android.parcel.RawValue
import java.util.*

@Parcelize
@Entity(tableName = "downloadsTable")
data class Download(
    @ColumnInfo(name = "attributes")
    @SerializedName("attributes")
    var attributes: Attributes,

    @ColumnInfo(name = "downloadDate")
    var downloadDate: Date,

    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    var id: Long? = null
) : Parcelable

@Parcelize
data class Attributes (
    @SerializedName("language") var language: String,

    @SerializedName("feature_details")
    var featureDetails: FeatureDetails,

    @SerializedName("files")
    var files: List<File>,

    @SerializedName("related_links")
    var relatedLinks: RelatedLinks
) : Parcelable

@Parcelize
data class FeatureDetails (
    @SerializedName("title") var movieTitle: String
) : Parcelable

@Parcelize
data class File(
    @SerializedName("file_name") var fileName: String
) : Parcelable

@Parcelize
data class RelatedLinks (
    @SerializedName("img_url") var posterImgUrl: String
) : Parcelable

@Parcelize
data class DownloadUrl (
    @SerializedName("link") var downloadUrl: String
) : Parcelable