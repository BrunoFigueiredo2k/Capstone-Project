package com.example.capstone_project.interfaces

import retrofit2.http.GET
import retrofit2.http.Query

interface SubtitlesApiService {
    // Get subtitles for movie
    @GET("/api/v1/subtitles")
    suspend fun fetchSubtitles(
        @Query("movie") movie : String
    )
}