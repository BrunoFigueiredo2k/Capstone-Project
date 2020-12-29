package com.example.capstone_project.model

import android.os.Parcelable
import androidx.annotation.DrawableRes
import androidx.room.Entity
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Movie (
    @SerializedName("title") var title: String,
    @SerializedName("id") var id: String,
    @SerializedName("release_date") var releaseYear: String,
    @SerializedName("genre_ids") var genreIds: ArrayList<Int>,
    @SerializedName("vote_average") var rating: Float,
    @SerializedName("poster_path") var posterImg: String
) : Parcelable
{
    // Function to get poster image file name
    fun getImageUrl() = "https://image.tmdb.org/t/p/w500${posterImg}"

    // TODO: Remove since it's not the proper way to do this api call
//    // Function to get poster image file name
//    fun getMovieImdbId() = "https://api.themoviedb.org/3/movie/${id}/external_ids?api_key=da007e76d36aca68e174f2948e09389c"
}