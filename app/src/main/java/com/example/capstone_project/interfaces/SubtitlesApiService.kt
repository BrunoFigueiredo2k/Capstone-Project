package com.example.capstone_project.interfaces

import com.example.capstone_project.model.ResultSetWithDownloads
import com.example.capstone_project.model.ResultSetWithMovies
import retrofit2.http.GET
import retrofit2.http.Query

interface SubtitlesApiService {
    // Get subtitles for movie
    // TODO: fix api call with api key and query param imdb_id
    @GET("/api/v1/subtitles")
    suspend fun fetchMovieSubtitles(
        @Query("imdb_id") imdbId : String
    ): ResultSetWithDownloads

    companion object{
        var apiKey = "7aIwmzOfqInNK9mYkPW7KTQ0lH7LdCTx"
    }
}