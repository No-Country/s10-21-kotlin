package com.example.nocountrydonation.di.module

import com.google.firebase.auth.FirebaseAuth
import org.koin.dsl.module

val appModule= module {
    single {
        FirebaseAuth.getInstance()
    }
}