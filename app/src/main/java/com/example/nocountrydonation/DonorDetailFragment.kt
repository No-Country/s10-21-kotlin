package com.example.nocountrydonation

import android.Manifest
import android.annotation.SuppressLint
import android.content.pm.PackageManager
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.app.ActivityCompat
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.bumptech.glide.Glide
import com.example.nocountrydonation.databinding.FragmentDonorDetailBinding
import com.example.nocountrydonation.feature_donors.domain.Donors
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationServices
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng

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
        binding?.toolbar?.setOnClickListener {
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