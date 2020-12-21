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
    @GET("/api/v1/find/movie")
    suspend fun fetchAllMovies(
        @Query("movie_name") name: String
    ): ResultSetWithMovies // TODO: check if has to be wrapped in array list class

    // TODO fix getting api key from secrets.xml
//    companion object {
//        private lateinit var context: Context
//        @SuppressLint("StringFormatInvalid")
//        var secretValue: String = context.getString(R.string.)
//    }
}