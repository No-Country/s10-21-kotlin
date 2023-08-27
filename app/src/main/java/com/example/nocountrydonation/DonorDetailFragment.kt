package com.example.nocountrydonation

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.navArgs
import com.bumptech.glide.Glide
import com.example.nocountrydonation.databinding.FragmentDonorDetailBinding
import com.example.nocountrydonation.feature_donors.domain.Donors

class DonorDetailFragment : Fragment() {
    private var binding : FragmentDonorDetailBinding? = null
    private val args : DonorDetailFragmentArgs by navArgs()
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentDonorDetailBinding.inflate(inflater, container, false)
        bindDonorsDetail(args.detail)
        return binding?.root
    }
    private fun bindDonorsDetail(donors: Donors){
        binding?.apply {
            textViewDonorsName.text = donors.name
            Glide.with(root.context).load(donors.image).into(imageViewProfile)
        }

    }

}