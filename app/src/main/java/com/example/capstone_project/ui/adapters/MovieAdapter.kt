package com.example.capstone_project.ui.adapters

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.capstone_project.R
import com.example.capstone_project.data.Movie
import kotlinx.android.synthetic.main.item_movie.view.*

class MovieAdapter(private val movies: List<Movie>) : RecyclerView.Adapter<MovieAdapter.ViewHolder>() {

    /** Creates and returns a ViewHolder object, inflating a standard layout called simple_list_item_1.*/
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.item_movie, parent, false)
        )
    }

    /** Returns the size of the list */
    override fun getItemCount(): Int {
        return movies.size
    }

    /** Called by RecyclerView to display the data at the specified position. */
    override fun onBindViewHolder(holder: ViewHolder, position: Int){
        holder.databind(movies[position])
    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        // TODO: check how to bind image from api
        // Bind all data to recyclerview item per movie
        @SuppressLint("SetTextI18n")
        fun databind(movie: Movie) {
            itemView.tvMovieTitle.text = movie.title
            itemView.tvMovieReleaseYear.text = movie.releaseYear.toString()
            itemView.tvMovieGenre.text = movie.genre
            itemView.tvMovieRating.text = movie.rating.toString()
            itemView.ivMoviePoster.setImageResource(movie.posterImg)
        }
    }
}