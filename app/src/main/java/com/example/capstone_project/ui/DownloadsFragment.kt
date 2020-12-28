package com.example.capstone_project.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.capstone_project.R
import com.example.capstone_project.model.Download
import com.example.capstone_project.model.Movie
import com.example.capstone_project.ui.adapter.DownloadsAdapter
import com.example.capstone_project.ui.adapter.MovieAdapter
import kotlinx.android.synthetic.main.fragment_downloads.*
import kotlinx.android.synthetic.main.fragment_movies.*

class DownloadsFragment : Fragment() {
    private val downloads = arrayListOf<Download>()
    private val viewModel: DownloadViewModel by viewModels()
    private val downloadAdapter = DownloadsAdapter(downloads)

    private fun observeDownloads() {
        viewModel.downloads.observe(viewLifecycleOwner, Observer {download ->
            downloads.clear()
            downloads.addAll(download)
            downloadAdapter.notifyDataSetChanged()
        })
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_downloads, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // Fetch all popular movies on on create view
        fetchDownloadsSubtitles()

        rvDownloads.layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
        rvDownloads.addItemDecoration(DividerItemDecoration(context, DividerItemDecoration.VERTICAL))
        rvDownloads.adapter = downloadAdapter

        // Update observer/adapter for any changes in downloaded movie subtitles
        observeDownloads()
    }

    private fun fetchDownloadsSubtitles(){

    }

}