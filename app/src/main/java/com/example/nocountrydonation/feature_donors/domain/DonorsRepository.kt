package com.example.nocountrydonation.feature_donors.domain

import com.example.nocountrydonation.util.ResultState

interface DonorsRepository {
    suspend fun getDonors(): ResultState<List<Donors>>
}