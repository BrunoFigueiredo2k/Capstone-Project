package com.example.capstone_project.repository

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.capstone_project.interfaces.MovieApiService
import com.example.capstone_project.interfaces.SubtitlesApiService
import com.example.capstone_project.model.*
import com.example.capstone_project.ui.api.MovieApi
import com.example.capstone_project.ui.api.SubtitleApi
import kotlinx.coroutines.withTimeout

class SubtitleRepository {
    private val subtitlesApiService: SubtitlesApiService = SubtitleApi.createApi()

    private val _downloads: MutableLiveData<ArrayList<Download>> = MutableLiveData()

    val download: LiveData<ArrayList<Download>>
        get() = _downloads

    suspend fun getSubtitlesForMovie(imdbId : String)  {
        try {
            //timeout the request after 5 seconds
            val result : ResultSetWithDownloads = withTimeout(5_000) {
                subtitlesApiService.fetchMovieSubtitles(imdbId)
            }

            _downloads.value = result.downloads
        } catch (error: Throwable) {
            throw MovieRefreshError("Unable to refresh subtitles for movies", error)
        }
    }

    private val _url: MutableLiveData<String> = MutableLiveData()

    val url: LiveData<String>
        get() = _url

    suspend fun getDownloadUrl(fileId : Long)  {
        try {
            //timeout the request after 5 seconds
            val result  = withTimeout(5_000) {
                subtitlesApiService.fetchDownloadUrl(fileId)
            }

            Log.d("urlDownload", result.downloadUrl)

            _url.value = result.downloadUrl
        } catch (error: Throwable) {
            throw MovieRefreshError("Unable to refresh download url", error)
        }
    }


    class MovieRefreshError(message: String, cause: Throwable) : Throwable(message, cause)
}