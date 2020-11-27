package com.example.capstone_project.ui.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.capstone_project.R
import com.example.capstone_project.model.Movie
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
//        rvMovies.layoutManager =
//            LinearLayoutManager(context, RecyclerView.VERTICAL, false)
//        rvMovies.adapter = gameAdapter
//        rvMovies.addItemDecoration(DividerItemDecoration(context, DividerItemDecoration.VERTICAL))

        val exampleList = generateDummyList(500)
        rvMovies.adapter = MovieAdapter(exampleList)
        rvMovies.layoutManager = LinearLayoutManager(context)
        rvMovies.setHasFixedSize(true)
    }

    private fun generateDummyList(size: Int): List<Movie> {
        val list = ArrayList<Movie>()
        for (i in 0 until size) {
            val drawable = when (i % 3) {
                0 -> R.drawable.ic_baseline_star_24
                1 -> R.drawable.ic_baseline_search_24
                else -> R.drawable.ic_launcher_background
            }
            val item = Movie("Spiderman 2", 2020, i, drawable)
            list += item
        }
        return list
    }

}