package com.example.capstone_project.data

import androidx.annotation.DrawableRes
import com.google.gson.annotations.SerializedName
import java.util.*

// TODO: add the rest of the
data class Movie (
    @SerializedName("title") var title: String,
    @SerializedName("releaseYear") var releaseYear: Int,
    @SerializedName("genre") var genre: String,
    @SerializedName("rating") var rating: Int,
    @DrawableRes @SerializedName("posterImgUrl") var posterImg: Int, // TODO: check if this the right way to save image from API
    @SerializedName("fileName") var fileName: String
)