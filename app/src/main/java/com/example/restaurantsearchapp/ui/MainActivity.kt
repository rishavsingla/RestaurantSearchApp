package com.example.restaurantsearchapp.ui

import android.os.Bundle
import android.widget.SearchView
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.DividerItemDecoration
import com.example.restaurantsearchapp.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private var binding: ActivityMainBinding? = null
    private lateinit var searchResultAdapter: SearchedResultAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding?.root
        setContentView(view)

        val viewModel: MainActivityViewModel by viewModels()

        viewModel.readRestaurantsJson()

        viewModel.readMenuJson()

        searchResultAdapter = SearchedResultAdapter()
        binding?.apply {
            recyclerView.adapter = searchResultAdapter
            searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
                override fun onQueryTextSubmit(query: String): Boolean {
                    viewModel.performSearch(query)
                    return false
                }

                override fun onQueryTextChange(newText: String): Boolean {
                    viewModel.performSearch(newText)
                    return false
                }

            })
        }

        viewModel.getSearchedResultList().observe(this) {
            searchResultAdapter.submitList(it)
        }
    }

    override fun onDestroy() {
        binding = null
        super.onDestroy()
    }
}

