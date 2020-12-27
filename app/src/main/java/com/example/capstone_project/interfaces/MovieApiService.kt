package com.example.capstone_project.interfaces

import android.R
import android.annotation.SuppressLint
import android.content.Context
import com.example.capstone_project.model.ResultSetWithMovies
import retrofit2.http.GET
import retrofit2.http.Query

interface MovieApiService {
    // TODO: correct endpoint need to be put here
    // The GET method needed to retrieve our movie data
    @GET("/3/search/movie?api_key=da007e76d36aca68e174f2948e09389c")
    suspend fun fetchSearchedMovies(
        @Query("query") name: String
    ): ResultSetWithMovies // TODO: check if has to be wrapped in array list class

    @GET("https://api.themoviedb.org/3/movie/popular?api_key=da007e76d36aca68e174f2948e09389c&language=en-US&page=1")
    suspend fun fetchAllMovies() : ResultSetWithMovies
}