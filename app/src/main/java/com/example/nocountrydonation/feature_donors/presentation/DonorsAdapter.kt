package com.example.nocountrydonation.feature_donors.presentation

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import coil.load
import coil.target.ImageViewTarget
import com.bumptech.glide.Glide
import com.example.nocountrydonation.R
import com.example.nocountrydonation.databinding.DonorsItemBinding
import com.example.nocountrydonation.feature_donors.domain.Donors

class DonorsAdapter(private val list: List<Donors>, private val onClickedDonors : OnDonorClicked) :
    RecyclerView.Adapter<DonorsAdapter.ViewHolder>() {

    interface OnDonorClicked{
        fun OnDonorClickListener(donors: Donors, position: Int)
    }
    inner class ViewHolder(private val binding: DonorsItemBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(donors: Donors) {
            binding.apply {
                textViewLocation.text = donors.city
                textViewTipeBlood.text = donors.blood
                textViewNameDonors.text = donors.name
                //Glide.with(root.context).load(donors.image).into(shapeableImageViewDonors)
                shapeableImageViewDonors.load(donors.image){
                    placeholder(R.drawable.error)
                    crossfade(true)
                    crossfade(300)
                }
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DonorsAdapter.ViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding = DonorsItemBinding.inflate(layoutInflater, parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: DonorsAdapter.ViewHolder, position: Int) {
        //val list2 = list[position]
        holder.bind(list[position])
        holder.itemView.setOnClickListener {
            onClickedDonors.OnDonorClickListener(list[position], position)
        }
    }

    override fun getItemCount() = list.size
}