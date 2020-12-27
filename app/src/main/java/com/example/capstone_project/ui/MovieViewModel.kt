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

    // Function to fetch all movies once the page loads
    fun fetchMovies() {
        viewModelScope.launch {
            try {
                movieRepository.fetchAllMovies()
            } catch (error: MovieRepository.MovieRefreshError) {
                _errorText.value = error.message
                Log.e("Movie error", error.cause.toString())
            }
        }
    }

    // Function to fetch movie based on search value
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