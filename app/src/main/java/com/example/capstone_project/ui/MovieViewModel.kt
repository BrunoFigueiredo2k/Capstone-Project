package com.example.capstone_project.ui

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.capstone_project.repository.MovieRepository
import kotlinx.coroutines.launch

class MovieViewModel : ViewModel(){
    private val movieRepository = MovieRepository()

    val movies = movieRepository.movie

    private val _errorText: MutableLiveData<String> = MutableLiveData()

    val errorText: LiveData<String>
        get() = _errorText

    fun getSearchedMovies(title: String) {
        viewModelScope.launch {
            try {
                movieRepository.getMovieItem(title)

            } catch (error: MovieRepository.MovieRefreshError) {
                _errorText.value = error.message
                Log.e("Movie error", error.cause.toString())
            }
        }
    }
}