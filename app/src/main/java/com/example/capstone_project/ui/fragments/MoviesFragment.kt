package com.example.capstone_project.ui.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.capstone_project.R
import com.example.capstone_project.data.Movie
import com.example.capstone_project.ui.adapters.MovieAdapter
import kotlinx.android.synthetic.main.fragment_movies.*


class MoviesFragment : Fragment() {
    private val movies = arrayListOf<Movie>()
    private val gameAdapter = MovieAdapter(movies)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        setHasOptionsMenu(true)
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_movies, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initViews()
    }

    private fun initViews() {
        // Initialize the recycler view with a linear layout manager, adapter
        rvMovies.layoutManager =
            LinearLayoutManager(context, RecyclerView.VERTICAL, false)
        rvMovies.adapter = gameAdapter
        rvMovies.addItemDecoration(DividerItemDecoration(context, DividerItemDecoration.VERTICAL))
    }

}