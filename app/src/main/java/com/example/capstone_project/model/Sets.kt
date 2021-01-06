package com.example.capstone_project.model

import com.google.gson.annotations.SerializedName

// ArrayList class for TMDB movies
data class ResultSetWithMovies (
    @SerializedName("results") var movies: ArrayList<Movie>
)

// ArrayList class for opensubtitles subtitles
data class ResultSetWithDownloads (
    @SerializedName("data") var downloads: ArrayList<Download>
)

// Arraylist of Genres in json response for genre names
data class ResultSetWithGenres (
    @SerializedName("genres") var genres: ArrayList<Genre>
)