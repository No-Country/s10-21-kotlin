package com.example.nocountrydonation.feature_authentication.signup

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.nocountrydonation.R
import com.example.nocountrydonation.databinding.FragmentRegisterBinding
import com.example.nocountrydonation.feature_authentication.signup.domain.NewUser
import com.google.android.material.snackbar.Snackbar
import com.google.firebase.auth.FirebaseAuth
import org.koin.android.ext.android.get

class RegisterFragment : Fragment() {
    private var binding: FragmentRegisterBinding? = null
    private lateinit var user: NewUser
    private val auth = get<FirebaseAuth>()
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentRegisterBinding.inflate(inflater, container, false)
        signup()
        return binding?.root
    }

    private fun signup() {
        binding?.apply {
            buttonRegister.setOnClickListener {
                user = NewUser(
                    etEmail.text.toString(),
                    etPass.text.toString(),
                    etName.text.toString()
                )
                val verification = listOf(user.email, user.password, user.name).any { it.isBlank() }
                if (verification) {
                    snackBar("Por favor llene todos los campos")
                } else {
                    auth.createUserWithEmailAndPassword(user.email, user.password)
                        .addOnCompleteListener {
                            if (it.isSuccessful) {
                                findNavController().navigate(R.id.action_registerFragment_to_homeFragment)
                                snackBar("Bienvenido: ${user.email}")
                            }
                        }.addOnFailureListener {
                            snackBar("Hubo un error intenta nuevamente")
                        }
                }
            }
        }
    }

    private fun snackBar(message: String) {
        binding?.frameLayout6?.let { Snackbar.make(it, message, Snackbar.LENGTH_SHORT).show() }
    }

}