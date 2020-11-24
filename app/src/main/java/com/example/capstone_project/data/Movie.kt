package com.example.capstone_project.data

import androidx.annotation.DrawableRes
import com.google.gson.annotations.SerializedName
import java.util.*

// TODO: add the rest of the
data class Movie (
    @SerializedName("title") var text: String,
    @SerializedName("releaseYear") var releaseYear: Int,
    @SerializedName("rating") var rating: Int,
    @SerializedName("posterImgUrl") var posterImgUrl: String,
    @SerializedName("fileName") var fileName: String
)