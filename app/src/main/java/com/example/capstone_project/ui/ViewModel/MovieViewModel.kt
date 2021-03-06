package com.example.capstone_project.ui.ViewModel

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
    val movieId = movieRepository.movieId
    val genres = movieRepository.genre

    private val _errorText: MutableLiveData<String> = MutableLiveData()

    val errorText: LiveData<String>
        get() = _errorText

    // Function to fetch all movies once the page loads
    fun fetchMovies() {
        viewModelScope.launch {
            try {
                movieRepository.fetchPopularMovies()
            } catch (error: MovieRepository.MovieRefreshError) {
                logError(error)
            }
        }
    }

    // Function to fetch movie based on search value
    fun getSearchedMovies(title: String) {
        viewModelScope.launch {
            try {
                movieRepository.getMovieItem(title)

            } catch (error: MovieRepository.MovieRefreshError) {
                logError(error)
            }
        }
    }

    fun getImdbId(id : String) : LiveData<String> {
        val imdbId = MutableLiveData<String>()
        viewModelScope.launch {
            try {
                movieRepository.fetchMovieImdbId(id)
                imdbId.postValue(movieId.value.toString())
            } catch (error: MovieRepository.MovieRefreshError) {
                logError(error)
            }
        }

        return imdbId
    }

    // Function to fetch all genre names
    fun getGenreNames() {
        viewModelScope.launch {
            try {
                val genres = movieRepository.getGenreNames()
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