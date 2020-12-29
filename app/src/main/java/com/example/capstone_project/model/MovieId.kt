package com.example.capstone_project.model

import com.google.gson.annotations.SerializedName

// Class to save imdb_id from json response
data class MovieId (
    @SerializedName("imdb_id") var imdbId: String
)