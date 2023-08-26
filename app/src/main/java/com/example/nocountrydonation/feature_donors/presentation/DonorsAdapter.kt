package com.example.nocountrydonation.feature_donors.presentation

import android.app.Activity
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.nocountrydonation.MainActivity
import com.example.nocountrydonation.databinding.DonorsItemBinding
import com.example.nocountrydonation.feature_donors.domain.Donors

class DonorsAdapter(private val list: List<Donors>) :
    RecyclerView.Adapter<DonorsAdapter.ViewHolder>() {
    inner class ViewHolder(private val binding: DonorsItemBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(donors: Donors) {
            binding.apply {
                textViewLocation.text = donors.city
                textViewTipeBlood.text = donors.blood
                textViewNameDonors.text = donors.name
                Glide.with(root.context).load(donors.image).into(shapeableImageViewDonors)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DonorsAdapter.ViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding = DonorsItemBinding.inflate(layoutInflater, parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: DonorsAdapter.ViewHolder, position: Int) {
        val list2 = list[position]
        holder.bind(list2)
    }

    override fun getItemCount() = list.size
}