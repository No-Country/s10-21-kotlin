package com.example.nocountrydonation.feature_home

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.example.nocountrydonation.R
import com.example.nocountrydonation.databinding.FragmentHomeBinding

class HomeFragment : Fragment() {
    private var binding : FragmentHomeBinding? = null
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentHomeBinding.inflate(inflater , container,false)
        binding?.buttonDonate?.setOnClickListener {
            findNavController().navigate(R.id.action_homeFragment_to_donateFragment42)
        }
        return binding?.root
    }
}