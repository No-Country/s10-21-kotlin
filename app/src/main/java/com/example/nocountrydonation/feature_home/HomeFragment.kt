package com.example.nocountrydonation.feature_home

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import com.example.nocountrydonation.R
import com.example.nocountrydonation.databinding.FragmentHomeBinding
import com.example.nocountrydonation.feature_donors.presentation.DonorsViewModel
import com.example.nocountrydonation.util.ResultState
import org.koin.androidx.viewmodel.ext.android.viewModel

class HomeFragment : Fragment() {
    private var binding : FragmentHomeBinding? = null
    private val viewModel : DonorsViewModel by viewModel()
    private lateinit var homeAdapter: HomeAdapter
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentHomeBinding.inflate(inflater , container,false)
        binding?.buttonDonate?.setOnClickListener {
            findNavController().navigate(R.id.action_homeFragment_to_donateFragment42)
        }
        binding?.buttonFindDonors?.setOnClickListener {
            findNavController().navigate(R.id.action_homeFragment_to_donorsFragment)
        }
        viewModel.getDonors().observe(viewLifecycleOwner, Observer { result ->
            when(result){
                is ResultState.Error -> binding?.progressBarHome?.isVisible = false
                is ResultState.Loading -> binding?.progressBarHome?.isVisible = true
                is ResultState.Success -> {
                    homeAdapter = HomeAdapter(result.data)
                    binding?.rvHome?.adapter = homeAdapter
                    binding?.progressBarHome?.isVisible = false
                }
            }
        })
        return binding?.root
    }
}