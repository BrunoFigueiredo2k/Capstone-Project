package com.example.capstone_project.model

import android.os.Parcelable
import androidx.annotation.DrawableRes
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Movie (
    @SerializedName("title") var title: String,
    @SerializedName("releaseYear") var releaseYear: Int,
//    @SerializedName("genre") var genre: String,
    @SerializedName("rating") var rating: Int,
    @DrawableRes @SerializedName("posterImgUrl") var posterImg: Int // TODO: check if this the right way to save image from API
//    @SerializedName("fileName") var fileName: String
) : Parcelable