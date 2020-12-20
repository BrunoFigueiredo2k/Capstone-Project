package com.example.capstone_project.model

import com.google.gson.annotations.SerializedName

data class ResultSetWithMovies (
    @SerializedName("results") var movies: ArrayList<Movie>
)
