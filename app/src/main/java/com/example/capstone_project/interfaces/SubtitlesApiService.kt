package com.example.capstone_project.interfaces

import com.example.capstone_project.model.DownloadUrl
import com.example.capstone_project.model.ResultSetWithDownloads
import retrofit2.http.*

interface SubtitlesApiService {
    // Get subtitles for movie based on passed imdb id
    @Headers("Api-Key: 7aIwmzOfqInNK9mYkPW7KTQ0lH7LdCTx")
    @GET("/api/v1/subtitles")
    suspend fun fetchMovieSubtitles(
        @Query("imdb_id") imdbId : String
    ): ResultSetWithDownloads

    // TODO: fix this
    // Get subtitle file download url
    @Headers(
        "Content-Type: application/json",
        "Authorization: ",
        "Api-Key: 7aIwmzOfqInNK9mYkPW7KTQ0lH7LdCTx"
    )
    @POST("/api/v1/download")
    suspend fun fetchDownloadUrl() : DownloadUrl
}