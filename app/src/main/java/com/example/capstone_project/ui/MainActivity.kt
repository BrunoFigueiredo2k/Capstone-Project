package com.example.capstone_project.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import android.view.Menu
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Toast
import com.example.capstone_project.R
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.content_main.*
import android.widget.SearchView;
import android.widget.Toast.LENGTH_LONG
import com.example.capstone_project.ui.adapter.ViewPagerAdapter

class MainActivity : AppCompatActivity() {

    private lateinit var adapter: ArrayAdapter<*>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(findViewById(R.id.toolbar))

        // Adapter for search function
        lv_listView.adapter = adapter
        lv_listView.onItemClickListener = AdapterView.OnItemClickListener { parent, view, position, id ->
            // TODO: change this click listener to opening subtitles list fragment
            Toast.makeText(applicationContext, parent?.getItemAtPosition(position).toString(), Toast.LENGTH_LONG).show()
        }
        lv_listView.emptyView = tv_emptyTextView

        // Set up top tabs
        setUpTabs()
    }

    // Function that calls methods from ViewPagerAdapter to set up the tabs for 'Movies' and 'Downloads'
    private fun setUpTabs(){
        val adapter = ViewPagerAdapter(
            supportFragmentManager
        )
        adapter.addFragment(MovieFragment(), "Movies")
        adapter.addFragment(DownloadsFragment(), "Downloads")

        viewPager.adapter = adapter
        tabs.setupWithViewPager(viewPager)

        val currentFragment = adapter.getPageTitle(adapter.count - 1)
        Toast.makeText(this, "Current Fragment: $currentFragment", LENGTH_LONG).show()
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.menu_main, menu)

        // TODO: only display on Movies fragment
        // Search function for searchView in activity_main
        val search = menu?.findItem(R.id.nav_search)
        val searchView = search?.actionView as SearchView
        searchView.queryHint = "Search movies.."

        searchView.setOnQueryTextListener(object: SearchView.OnQueryTextListener{
            override fun onQueryTextSubmit(query: String?): Boolean {
                return false
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                adapter.filter.filter(newText)
                return true
            }
        })
        return true
    }
}