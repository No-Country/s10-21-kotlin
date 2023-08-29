package com.example.nocountrydonation

import android.annotation.SuppressLint
import android.app.AlertDialog
import android.app.Dialog
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.fragment.app.DialogFragment
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.fragment.findNavController
import com.example.nocountrydonation.databinding.FragmentSucessRequestBinding
import com.example.nocountrydonation.feature_home.HomeFragment

class FragmentSucessRequest : DialogFragment() {
    private lateinit var binding : FragmentSucessRequestBinding

        @SuppressLint("UseGetLayoutInflater")
    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        binding = FragmentSucessRequestBinding.inflate(LayoutInflater.from(context))
        val builder = AlertDialog.Builder(requireActivity())
        builder.setView(binding.root)
        binding.apply {
            imageViewNext.setOnClickListener {
                //val action = FragmentSucessRequestDirections.actionFragmentSucessRequestToHomeFragment()
//                val navHost = parentFragmentManager.findFragmentById(R.id.fragmentContainerView) as NavHostFragment
//                navHost.navController.navigate(R.id.action_fragmentSucessRequest_to_homeFragment)
                //findNavController().navigate(R.id.action_fragmentSucessRequest_to_homeFragment)
//                val home = HomeFragment()
//                val transaction = parentFragmentManager.beginTransaction()
//                transaction.replace(R.id.fragmentContainerView, home,"ventan")
//                transaction.commit()
                dismiss()
            }
            imageViewNext.setBackgroundColor(
                ContextCompat.getColor(requireContext(),R.color.transparent)
            )
        }
        val dialog = builder.create()
        dialog.window!!.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        return dialog
    }
}