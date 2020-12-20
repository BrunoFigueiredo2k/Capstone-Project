package com.example.capstone_project.interfaces

import retrofit2.http.GET
import retrofit2.http.Query

interface FlagApiService {
    // The GET method needed to retrieve the flags
    @GET("3/discover/movie?api_key=da007e76d36aca68e174f2948e09389c&language=en-US&sort_by=popularity.desc&include_adult=false&include_video=false&page=1")
    suspend fun fetchFlag(
        @Query("year") releaseYear: Int
    )
}