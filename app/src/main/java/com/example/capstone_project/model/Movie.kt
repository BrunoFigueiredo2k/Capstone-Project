package com.example.capstone_project.model

import android.os.Parcelable
import androidx.annotation.DrawableRes
import androidx.room.Entity
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Movie (
    @SerializedName("title") var title: String,
    @SerializedName("release_date")var releaseYear: String,
    @SerializedName("genre") var genre: String, // TODO: not in endpoint
    @SerializedName("vote_average") var rating: Float,
    @SerializedName("poster_path") var posterImg: String
) : Parcelable
{
    // Function to get poster image file name
    fun getImageUrl() = "https://image.tmdb.org/t/p/w500${posterImg}"
}