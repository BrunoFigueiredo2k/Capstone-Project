package com.example.capstone_project.interfaces

import android.R
import android.annotation.SuppressLint
import android.content.Context
import com.example.capstone_project.model.MovieId
import com.example.capstone_project.model.ResultSetWithGenres
import com.example.capstone_project.model.ResultSetWithMovies
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface MovieApiService {
    // GET request to fetch movies based on search input movie title (paramater)
    @GET("/3/search/movie?api_key=da007e76d36aca68e174f2948e09389c")
        suspend fun fetchSearchedMovies(
        @Query("query") name: String
    ): ResultSetWithMovies

    // GET request to fetch all popular movies
    @GET("/3/movie/popular?api_key=da007e76d36aca68e174f2948e09389c&language=en-US&page=1")
    suspend fun fetchPopularMovies() : ResultSetWithMovies

    // Function to get movie imdb id
    @GET("/3/movie/{id}/external_ids?api_key=da007e76d36aca68e174f2948e09389c")
    suspend fun getMovieImdbId(
        @Path("id") id : String
    ) : MovieId

    // GET request to fetch genres with names with corresponding ids
    @GET("/3/genre/movie/list?api_key=da007e76d36aca68e174f2948e09389c&language=en-US")
    suspend fun getGenreNames() : ResultSetWithGenres
}