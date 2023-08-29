package com.example.nocountrydonation.feature_search.presentation

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.example.nocountrydonation.R
import com.example.nocountrydonation.databinding.DonorsItemBinding
import com.example.nocountrydonation.feature_donors.domain.Donors
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.Query
import kotlinx.coroutines.tasks.await

class SearchAdapter(private var list: List<Donors>):RecyclerView.Adapter<SearchAdapter.ViewHolder>(){


    inner class ViewHolder(private val binding : DonorsItemBinding):RecyclerView.ViewHolder(binding.root){
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
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding = DonorsItemBinding.inflate(layoutInflater, parent, false)
        return ViewHolder(binding)
    }
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(list[position])
    }

    override fun getItemCount() = list.size
//    private fun getDonors(): Query {
//        return FirebaseFirestore.getInstance().collection("donantes")
//    }
//    @SuppressLint("NotifyDataSetChanged")
//    suspend fun filter(query: String?) {
//        if (query.isNullOrBlank()) {
//            list = getDonors().get().await().toObjects(Donors::class.java)
//        } else {
//            list = getDonors().whereEqualTo("blood", "$query").get().await().toObjects(Donors::class.java)
//        }
//        notifyDataSetChanged()
//    }
}
