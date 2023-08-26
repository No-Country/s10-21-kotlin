package com.example.nocountrydonation.feature_donors.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import com.example.nocountrydonation.feature_donors.domain.DonorsRepository
import com.example.nocountrydonation.util.ResultState
import kotlinx.coroutines.Dispatchers

class DonorsViewModel(private val donorsRepository: DonorsRepository) : ViewModel() {
    fun getDonors() = liveData(Dispatchers.IO) {
        emit(ResultState.Loading())
        try {
            emit(donorsRepository.getDonors())

        } catch (e: Exception) {
            emit(ResultState.Error(e))
        }
    }
}