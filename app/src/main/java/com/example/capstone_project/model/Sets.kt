package com.example.capstone_project.model

import com.google.gson.annotations.SerializedName

// Class for movie, wrapped in an arraylist to match api endpoint data
data class ResultSetWithMovies (
    @SerializedName("results") var movies: ArrayList<Movie>
)

// Arraylist of Download and Movie classes to match api endpoint data
data class ResultSetWithDownloads (
    @SerializedName("data") var downloads: ArrayList<Download>
)

data class ResultSetWithGenres (
    @SerializedName("genres") var genres: ArrayList<Genre>
)