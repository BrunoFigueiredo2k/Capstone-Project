package com.example.capstone_project.ui

import android.annotation.SuppressLint
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
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.capstone_project.R
import com.example.capstone_project.model.Movie
import com.example.capstone_project.ui.ViewModel.MovieViewModel
import com.example.capstone_project.ui.adapter.MovieAdapter
import kotlinx.android.synthetic.main.fragment_movies.*

class MovieFragment : Fragment() {
    private val movies = arrayListOf<Movie>()
    private val viewModel: MovieViewModel by viewModels()
    private val movieAdapter =
        MovieAdapter(movies) { movie ->
            onMovieClick(movie)
        }

    private fun observeMovies() {
        viewModel.movies.observe(viewLifecycleOwner, Observer {movie ->
            movies.clear()
            movies.addAll(movie)
            movieAdapter.notifyDataSetChanged()
        })
    }

    // TODO: returns null atm need to fix
    private fun observeGenres() {
        viewModel.genres.observe(viewLifecycleOwner, Observer {
            fetchMovieGenreNames()
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

        // Reset icon click, calls fetchMovies function which returns popular movies (starting list)
        reset_movies_icon.setOnClickListener{
            input_movie_title.getText()?.clear() // Reset input field text
            tvCurrentState.setText(R.string.current_state_display) // Reset to popular movies display text
            fetchMovies()
        }

        initViews()

        observeMovies()
        observeGenres()

        Log.d("genres", viewModel.genres.value.toString())
    }

    // Initialize views (adapter/recyclerview)
    fun initViews(){
        rvMovies.layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
        rvMovies.addItemDecoration(DividerItemDecoration(context, DividerItemDecoration.VERTICAL))
        rvMovies.adapter = movieAdapter
    }

    // Fetch all popular movies when activity is loaded
    private fun fetchMovies(){
        viewModel.fetchMovies()
    }

    // TODO: returns null atm need to fix observer
    // Fetch subtitles for passed movie based on imdb_id
    private fun fetchMovieGenreNames() {
        val genres = viewModel.getGenreNames()
        Log.d("genres", genres.toString())
    }

    // Function to fetch movies from API with movie title search value as param
    @SuppressLint("SetTextI18n")
    private fun fetchMoviesByName(){
        val titleMovie  = input_movie_title.text.toString()
        if (titleMovie.isNotBlank()){
            viewModel.getSearchedMovies(titleMovie)
            tvCurrentState.setText("currently displaying: " + input_movie_title.getText())
        } else {
            Toast.makeText(context, "Input is empty", Toast.LENGTH_LONG).show()
            Log.d("emptyInput", "Input is empty")
        }
    }

    // Click listener for specific movie
    private fun onMovieClick(movie: Movie) {
        // Open available subtitles for clicked movie
        val intent = Intent(context, SubtitlesActivity::class.java)
        intent.putExtra("movie", movie)
        startActivity(intent)
    }

}