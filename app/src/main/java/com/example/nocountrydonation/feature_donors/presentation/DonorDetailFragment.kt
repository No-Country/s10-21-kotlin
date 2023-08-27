package com.example.nocountrydonation.feature_donors.presentation

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.bumptech.glide.Glide
import com.example.nocountrydonation.MainActivity
import com.example.nocountrydonation.R
import com.example.nocountrydonation.databinding.FragmentDonorDetailBinding
import com.example.nocountrydonation.feature_donors.domain.Donors

class DonorDetailFragment : Fragment() {
    private var binding: FragmentDonorDetailBinding? = null
    private val args: DonorDetailFragmentArgs by navArgs()
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentDonorDetailBinding.inflate(inflater, container, false)
        bindDonorsDetail(args.detail)
        binding?.buttonPermission?.setOnClickListener {
            (activity as MainActivity).showPermission()
        }
        binding?.buttonShowMaps?.setOnClickListener {
            findNavController().navigate(R.id.action_donorDetailFragment_to_mapsFragment)
        }
        binding?.toolbarRequest?.setOnClickListener {
            findNavController().navigate(R.id.action_donorDetailFragment_to_donorsFragment)
        }

        return binding?.root
    }

    private fun bindDonorsDetail(donors: Donors) {
        binding?.apply {
            textViewDonorsName.text = donors.name
            textViewCity.text = donors.city
            textViewBloodType.text = donors.blood
            Glide.with(root.context).load(donors.image).into(imageViewProfile)
        }

    }

}