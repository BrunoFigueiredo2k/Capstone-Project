package com.example.capstone_project.interfaces

import com.example.capstone_project.model.ResultSetWithDownloads
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Query

interface SubtitlesApiService {
    // Get subtitles for movie based on passed imdb id
    @Headers("Api-Key: 7aIwmzOfqInNK9mYkPW7KTQ0lH7LdCTx")
    @GET("/api/v1/subtitles")
    suspend fun fetchMovieSubtitles(
        @Query("imdb_id") imdbId : String
    ): ResultSetWithDownloads
}