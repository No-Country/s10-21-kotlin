package com.example.nocountrydonation.di

import android.app.Application
import com.example.nocountrydonation.di.module.appModule
import org.koin.core.context.startKoin

class DonationApp: Application() {
    override fun onCreate() {
        super.onCreate()
        startKoin {
            modules(appModule)
        }
    }
}