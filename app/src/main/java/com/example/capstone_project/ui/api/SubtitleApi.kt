package com.example.capstone_project.ui.api

import com.example.capstone_project.interfaces.MovieApiService
import com.example.capstone_project.interfaces.SubtitlesApiService
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class SubtitleApi {
    companion object {
        // The base url off the api.
        private const val baseUrl = "https://www.opensubtitles.com"

        fun createApi(): SubtitlesApiService {
            // Create an OkHttpClient to be able to make a log of the network traffic
            val okHttpClient = OkHttpClient.Builder()
                .addInterceptor(HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY))
                .build()

            // Create the Retrofit instance
            val subtitleApi = Retrofit.Builder()
                .baseUrl(baseUrl)
                .client(okHttpClient)
                .addConverterFactory(GsonConverterFactory.create())
                .build()

            // Return the Retrofit TriviaApiService
            return subtitleApi.create(SubtitlesApiService::class.java)
        }
    }
}