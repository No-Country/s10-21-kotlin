package com.example.nocountrydonation.feature_authentication.signup.domain

data class NewUser(
    val email: String,
    val password: String,
    val name: String
)
