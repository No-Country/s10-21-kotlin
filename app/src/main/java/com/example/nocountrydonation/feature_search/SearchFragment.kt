package com.example.nocountrydonation.feature_search

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.SearchView
import androidx.lifecycle.lifecycleScope
import com.example.nocountrydonation.databinding.FragmentSearchBinding
import com.example.nocountrydonation.feature_search.SearchAdapter
import kotlinx.coroutines.launch

class SearchFragment : Fragment() {
    private var binding : FragmentSearchBinding? = null
    private lateinit var adapterSearch : SearchAdapter
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentSearchBinding.inflate(inflater, container, false)
        adapterSearch = SearchAdapter()
        binding?.rvSearch?.adapter = adapterSearch

        binding?.searchView2?.setOnQueryTextListener(object : android.widget.SearchView.OnQueryTextListener,
            SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?) = false
            override fun onQueryTextChange(newText: String?): Boolean {
                lifecycleScope.launch { adapterSearch.filter(newText) }
                return true
            }

        })
        return binding?.root
    }

}