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
    @ColumnInfo(name = "featureDetails")
    @SerializedName("feature_details")
    var featureDetails: @RawValue FeatureDetails,

    @ColumnInfo(name = "attributes")
    @SerializedName("attributes")
    var attributes: @RawValue Attributes,

    @ColumnInfo(name = "files")
    @SerializedName("files")
    var files: List<Files>,

    @ColumnInfo(name = "relatedLinks")
    @SerializedName("related_links")
    var relatedLinks: @RawValue RelatedLinks,

    @ColumnInfo(name = "downloadDate")
    var downloadDate: Calendar,

    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    var id: Long? = null
) : Parcelable

@Parcelize
data class FeatureDetails (
    @SerializedName("title") var movieTitle: String
) : Parcelable

@Parcelize
data class Attributes (
    @SerializedName("language") var language: String
) : Parcelable

@Parcelize
data class Files(
    @SerializedName("file_name") var fileName: String
) : Parcelable

@Parcelize
data class RelatedLinks (
    @SerializedName("img_url") var posterImgUrl: String
) : Parcelable