package com.example.capstone_project.ui.adapter

import android.annotation.SuppressLint
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.capstone_project.R
import com.example.capstone_project.model.Download
import com.example.capstone_project.model.Movie
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
            itemView.tvMovieReleaseYear.text = movie.releaseYear.toString()
            itemView.tvMovieGenre.text = movie.genreIds.toString()
            itemView.tvMovieRating.text = movie.rating.toString()

            Glide.with(context).load(movie.getImageUrl()).into(itemView.ivMoviePoster)
        }
    }
}