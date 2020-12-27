package com.example.capstone_project.repository

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.capstone_project.interfaces.MovieApiService
import com.example.capstone_project.model.Movie
import com.example.capstone_project.model.ResultSetWithMovies
import com.example.capstone_project.ui.api.MovieApi
import kotlinx.coroutines.withTimeout

class MovieRepository {
    private val movieApiService: MovieApiService = MovieApi.createApi()

    private val _movies: MutableLiveData<ArrayList<Movie>> = MutableLiveData()

    val movie: LiveData<ArrayList<Movie>>
    get() = _movies

    suspend fun fetchAllMovies(){
        try {
            //timeout the request after 5 seconds
            val result : ResultSetWithMovies = withTimeout(5_000) {
                movieApiService.fetchAllMovies()
            }

            _movies.value = result.movies
            Log.d("movies", result.toString())
        } catch (error: Throwable) {
            throw MovieRefreshError("Unable to refresh movies", error)
        }
    }

    suspend fun getMovieItem(title : String)  {
        try {
            //timeout the request after 5 seconds
            val result : ResultSetWithMovies = withTimeout(5_000) {
                movieApiService.fetchSearchedMovies(title)
            }

            _movies.value = result.movies
            Log.d("movies", result.toString())
        } catch (error: Throwable) {
            throw MovieRefreshError("Unable to refresh movies", error)
        }
    }

    class MovieRefreshError(message: String, cause: Throwable) : Throwable(message, cause)
}