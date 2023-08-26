package com.example.nocountrydonation.feature_donors.data

import com.example.nocountrydonation.feature_donors.data.network.DonorService
import com.example.nocountrydonation.feature_donors.domain.Donors
import com.example.nocountrydonation.feature_donors.domain.DonorsRepository
import com.example.nocountrydonation.util.ResultState

class DonorsRepositoryImp(private val donorService: DonorService):DonorsRepository {
    override suspend fun getDonors(): ResultState<List<Donors>> = donorService.getDonor()
}