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

    fun convertGenreIdsToName(ids : ArrayList<Int>) : String {
        var genres = arrayListOf<String>()

        val genreNames = ""

        Log.d("genreNames", genreNames.toString())

        // TODO: return concatenated genres here
        return ""
    }
}