package com.example.capstone_project.interfaces

import com.example.capstone_project.model.ResultSetWithMovies
import retrofit2.http.GET
import retrofit2.http.Query

interface MovieApiService {
    // TODO: correct endpoint need to be put here
    // The GET method needed to retrieve our movie data
    @GET("/api/v1/subtitles")
    suspend fun fetchAllMovies(
        @Query("movie_name") name: String
    ): ResultSetWithMovies // TODO: check if has to be wrapped in array list class
}