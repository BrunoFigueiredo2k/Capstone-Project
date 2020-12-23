package com.example.capstone_project.model

import android.os.Parcelable
import androidx.annotation.DrawableRes
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Movie (
    // TODO: check endpoint for tmdb api for annotations
    @SerializedName("title") var title: String,
    @SerializedName("year") var releaseYear: Int,
    @SerializedName("genre") var genre: String, // TODO: not in endpoint
    @SerializedName("ratings") var rating: Int,
    @SerializedName("img_url") var posterImg: String
) : Parcelable
{
    // Function to get backdrop image file name
    fun getImageUrl() = "https://image.tmdb.org/t/p/w500${posterImg}"
}