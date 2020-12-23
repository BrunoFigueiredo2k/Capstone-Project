package com.example.capstone_project.ui

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.capstone_project.R
import com.example.capstone_project.model.Movie
import com.example.capstone_project.ui.adapter.MovieAdapter
import kotlinx.android.synthetic.main.fragment_movies.*


class MovieFragment : Fragment() {
    private val movies = arrayListOf<Movie>()
    private val viewModel: MovieViewModel by viewModels()
    private val movieAdapter =
        MovieAdapter(movies) { movie ->
            onMovieClick(movie)
        }
    lateinit var layoutManager: LinearLayoutManager

    private fun observeMovies() {
        viewModel.movies.observe(viewLifecycleOwner, Observer {movie ->
            movies.clear()
            movies.addAll(movie)
            movieAdapter.notifyDataSetChanged()
        })
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_movies, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // Fetch all popular movies on on create view
        fetchMovies()

        // Submit button click list movies recyclerview
        search_btn.setOnClickListener{
            fetchMoviesByName()
        }

        rvMovies.layoutManager = GridLayoutManager(context, 2)
        rvMovies.adapter = movieAdapter

        observeMovies()
    }

    // TODO: fetch all movies when app is loaded
    private fun fetchMovies(){

    }

    // Function to fetch movies from API with movie title search value as param
    private fun fetchMoviesByName(){
        val titleMovie  = input_movie_title.text.toString()
        if (titleMovie.isNotBlank()){
            viewModel.getSearchedMovies(titleMovie)
        } else {
            Toast.makeText(context, "Input is empty", Toast.LENGTH_LONG).show()
            Log.d("emptyInput", "Input is empty")
        }
    }

    // Click listener for specific movie
    private fun onMovieClick(movie: Movie) {
        // Open available subtitles for clicked movie
        val intent = Intent(context, SubtitlesFragment::class.java)
        intent.putExtra("movie", movie)
        startActivity(intent)
    }

}