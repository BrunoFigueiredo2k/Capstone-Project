package com.example.capstone_project.ui.adapter

import android.annotation.SuppressLint
import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.viewModels
import androidx.lifecycle.LiveData
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.capstone_project.R
import com.example.capstone_project.interfaces.MovieApiService
import com.example.capstone_project.model.*
import com.example.capstone_project.ui.ViewModel.MovieViewModel
import com.example.capstone_project.ui.api.MovieApi
import kotlinx.android.synthetic.main.item_movie.view.*

class MovieAdapter(private val movies: List<Movie>, private val onClick: (Movie) -> Unit) :
    RecyclerView.Adapter<MovieAdapter.ViewHolder>() {

    private lateinit var context: Context

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieAdapter.ViewHolder {
        context = parent.context

        return ViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.item_movie, parent, false)
        )
    }

    override fun getItemCount(): Int = movies.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) = holder.databind(movies[position])

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        init {
            itemView.setOnClickListener { onClick(movies[adapterPosition]) }
        }
        @SuppressLint("SetTextI18n")
        fun databind(movie: Movie) {
            itemView.tvMovieTitle.text = movie.title
            itemView.tvMovieReleaseYear.text = movie.releaseYear
            // TODO: call convertGenreIdsToName(movie.genreIds) on assignment
            itemView.tvMovieGenre.text = convertGenreIdsToName(movie.genreIds)
            itemView.tvMovieRating.text = movie.rating.toString()

            Glide.with(context).load(movie.getImageUrl()).into(itemView.ivMoviePoster)
        }
    }

    // TODO: fix this function
    // Converts genre ids array passed to the corresponding genre names from TMDB
    fun convertGenreIdsToName(ids : ArrayList<Int>) : String {
        val genresIds = arrayListOf<Int>() // TODO: get viewmodel data from MovieApiService
        val genreNames = arrayListOf<String>() // TODO: get viewmodel data from MovieApiService
        var returnGenreNames = ""

        // Loop through each ids of spefific movie
        for (i in ids.indices){
            // Loop through all genreIds in response from api and check if it equals the passed id index
            for (j in genreNames.indices){
                // If passed id index is equal to genre id in object
                if (ids[j] == genresIds[j]){
                    // Add the genre to the empty return string with a comma if it's not the last one
                    if (j == (genreNames.size - 1)){
                        returnGenreNames += genreNames[j]
                    } else {
                        returnGenreNames += genreNames[j] + ", "
                    }
                }
            }
        }

        Log.d("genreNames", genreNames.toString())

        // TODO: return concatenated genres here
        return returnGenreNames
    }
}