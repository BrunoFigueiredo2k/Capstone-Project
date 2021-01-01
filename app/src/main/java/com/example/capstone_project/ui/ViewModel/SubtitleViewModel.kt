package com.example.capstone_project.ui.ViewModel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.capstone_project.repository.MovieRepository
import com.example.capstone_project.repository.SubtitleRepository
import kotlinx.coroutines.launch

class SubtitleViewModel : ViewModel() {
    private val subtitleRepository = SubtitleRepository()

    val downloads = subtitleRepository.download

    private val _errorText: MutableLiveData<String> = MutableLiveData()

    val errorText: LiveData<String>
        get() = _errorText

    // Function to fetch movie in coroutine
    fun fetchSubtitles(imdbId : String) {
        Log.d("fetchSubtitles", imdbId)
        viewModelScope.launch {
            try {
                subtitleRepository.getSubtitlesForMovie(imdbId)
            } catch (error: MovieRepository.MovieRefreshError) {
                logError(error)
            }
        }
    }

    fun logError(error : MovieRepository.MovieRefreshError){
        _errorText.value = error.message
        Log.e("Movie error", error.cause.toString())
    }
}