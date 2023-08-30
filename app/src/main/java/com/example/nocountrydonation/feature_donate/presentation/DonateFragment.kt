package com.example.nocountrydonation.feature_donate.presentation

import android.content.ContentValues
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import androidx.navigation.fragment.findNavController
import com.example.nocountrydonation.FragmentSucessRequest
import com.example.nocountrydonation.MainActivity
import com.example.nocountrydonation.R
import com.example.nocountrydonation.databinding.FragmentDonateBinding
import com.example.nocountrydonation.feature_donate.domain.Donor
import com.google.android.material.snackbar.Snackbar
import com.google.firebase.Timestamp
import com.google.firebase.firestore.FirebaseFirestore
import org.koin.android.ext.android.get
import java.util.Date

class DonateFragment : Fragment() {
    private var binding : FragmentDonateBinding? = null
    private lateinit var donor : Donor
    private val db = get<FirebaseFirestore>()
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentDonateBinding.inflate(inflater, container, false)
        (activity as MainActivity).showBottomNav(false)
        setAutocomplete()
        sendDonation()
        binding?.toolbarRequest?.setOnClickListener {
            findNavController().navigate(R.id.action_donateFragment4_to_homeFragment)
        }

        return binding?.root
    }
    private fun setAutocomplete(){
        val blood = resources.getStringArray(R.array.Blood)
        val arrayAdapter = ArrayAdapter(requireContext(),R.layout.dropdow_item, blood)
        binding?.autoCompleteBlood?.setAdapter(arrayAdapter)
        val city = resources.getStringArray(R.array.Citys)
        val cityAdapter = ArrayAdapter(requireContext(),R.layout.dropdow_item, city)
        binding?.autocompleteCity?.setAdapter(cityAdapter)
        val hospital = resources.getStringArray(R.array.Hospital)
        val hospitalAdapter = ArrayAdapter(requireContext(), R.layout.dropdow_item, hospital)
        binding?.autoCompleteHospital?.setAdapter(hospitalAdapter)
    }
    private fun sendDonation(){
        binding?.apply {
            buttonSend.setOnClickListener {
                donor = Donor(
                    autocompleteCity.text.toString(),
                    autoCompleteHospital.text.toString(),
                    autoCompleteBlood.text.toString(),
                    etPhone.text.toString(),
                    etNote.text.toString()
                )
                val verification = listOf(donor.city,donor.hospital,donor.blood,donor.name,donor.note).any{it.isBlank()}
                if(verification){
                    snackBar("Por favor llene todos los campos")
                }else{
                    val dato = hashMapOf(
                        "city" to donor.city,
                        "hospital" to donor.hospital,
                        "blood" to donor.blood,
                        "name" to donor.name,
                        "note" to donor.note,
                        "created_at" to Timestamp(Date()).toDate(),
                        "image" to "https://firebasestorage.googleapis.com/v0/b/nocountrydonation.appspot.com/o/perfil-del-usuario.png?alt=media&token=39f35c98-cc1f-42de-8459-ad595e672d35"
                    )
                    db.collection("donantes")
                        .document(donor.name)
                        .set(dato)
                        .addOnSuccessListener { documentReference ->
                            Log.d(
                                ContentValues.TAG,
                                "DocumentSnapshot added with ID: ${documentReference}"
                            )
                        }
                        .addOnFailureListener { e ->
                            Log.w(ContentValues.TAG, "Error adding document", e)
                        }
                    (activity as MainActivity).sendNotification("el usuario ${donor.name} acabada de donar","La donacion se hizo en el Hospital ${donor.hospital}")
                    snackBar("Se registro tu turno correctamente")
                    findNavController().navigate(R.id.action_donateFragment4_to_homeFragment)
                    FragmentSucessRequest().show(parentFragmentManager, "ventana")
                }
            }
        }
    }
    private fun snackBar(message: String) {
        binding?.frameLayout8?.let { Snackbar.make(it, message, Snackbar.LENGTH_SHORT).show() }
    }
}