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

    // TODO: retrofit2.HttpException: HTTP 406
    // Get subtitle file download url
    @Headers(
        "Content-Type: multipart/form-data",
        "Api-Key: 7aIwmzOfqInNK9mYkPW7KTQ0lH7LdCTx"
    )
    @POST("/api/v1/download")
    suspend fun fetchDownloadUrl(
        @Query("file_id") fileId : Long
    ) : DownloadUrl
}