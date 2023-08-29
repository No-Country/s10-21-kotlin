package com.example.nocountrydonation.feature_search.presentation

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.SearchView
import androidx.core.view.isVisible
import androidx.lifecycle.Observer
import androidx.lifecycle.lifecycleScope
import com.example.nocountrydonation.databinding.FragmentSearchBinding
import com.example.nocountrydonation.feature_donors.domain.Donors
import com.example.nocountrydonation.feature_search.presentation.SearchAdapter
import kotlinx.coroutines.launch
import org.koin.androidx.viewmodel.ext.android.viewModel

class SearchFragment : Fragment() {
    private var binding : FragmentSearchBinding? = null
    private lateinit var adapterSearch : SearchAdapter
    private val viewModel : SearchViewModel by viewModel()
    private lateinit var list : List<Donors>
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentSearchBinding.inflate(inflater, container, false)
//        adapterSearch = SearchAdapter()
//        binding?.rvSearch?.adapter = adapterSearch
//
//        binding?.searchView2?.setOnQueryTextListener(object : android.widget.SearchView.OnQueryTextListener,
//            SearchView.OnQueryTextListener {
//            override fun onQueryTextSubmit(query: String?) = false
//            override fun onQueryTextChange(newText: String?): Boolean {
//                lifecycleScope.launch { adapterSearch.filter(newText) }
//                return true
//            }
//
//        })
        setObserver()
        setupListener()
        return binding?.root
    }
    private fun setObserver(){
        viewLifecycleOwner.lifecycleScope.launch {
            viewModel.search.observe(viewLifecycleOwner, Observer {result->
                when(result){
                    is UiState.Error -> binding?.textView9Alert?.isVisible = false
                    is UiState.Loading -> binding?.textView9Alert?.isVisible = true
                    is UiState.Success -> {
                        adapterSearch = SearchAdapter(result.items)
                        binding?.rvSearch?.adapter = adapterSearch
                        binding?.textView9Alert?.isVisible = false
                    }
                }
            })
        }

    }
    private fun setupListener(){
        binding?.searchView?.setOnQueryTextListener(object : SearchView.OnQueryTextListener{
            //            override fun onQueryTextSubmit(query: String?) = false
//
//            override fun onQueryTextChange(newText: String?): Boolean {
//                newText?.let { viewModel.getSearch(it) }
//                return true
//            }
            override fun onQueryTextSubmit(query: String?): Boolean {
                query.let { viewModel.getSearch(query.toString()) }
                return true
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                return false
            }
        })
    }

}