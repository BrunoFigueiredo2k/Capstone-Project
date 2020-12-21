package com.example.capstone_project.interfaces

import retrofit2.http.GET
import retrofit2.http.Query


interface FlagApiService {
    // The GET method needed to retrieve the flags
    @GET("https://www.countryflags.io/")
    suspend fun fetchFlagImage(
        @Query("country_code") countryCode : String
    )
}