package com.example.nocountrydonation.feature_donors.presentation

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import com.example.nocountrydonation.MainActivity
import com.example.nocountrydonation.R
import com.example.nocountrydonation.databinding.FragmentDonorsBinding
import com.example.nocountrydonation.util.ResultState
import com.google.firebase.firestore.FirebaseFirestore
import org.koin.android.ext.android.get
import org.koin.androidx.viewmodel.ext.android.viewModel

class DonorsFragment : Fragment() {
    private var binding : FragmentDonorsBinding? = null
    private val db = get<FirebaseFirestore>()
    private lateinit var donorsAdapter : DonorsAdapter
    private val viewModel : DonorsViewModel by viewModel()
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentDonorsBinding.inflate(inflater, container, false)
        viewModel.getDonors().observe(viewLifecycleOwner, Observer { result ->
            when(result){
                is ResultState.Error -> binding?.progressBarDonors?.isVisible = false
                is ResultState.Loading -> binding?.progressBarDonors?.isVisible = true
                is ResultState.Success -> {
                    donorsAdapter = DonorsAdapter(result.data)
                    binding?.rvDonors?.adapter = donorsAdapter
                    binding?.progressBarDonors?.isVisible = false
                }
            }
        })
        binding?.toolbar?.setOnClickListener {
            findNavController().navigate(R.id.action_donorsFragment_to_homeFragment)
        }
        return binding?.root
    }

}