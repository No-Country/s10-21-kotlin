package com.example.nocountrydonation.feature_search.domain

import com.example.nocountrydonation.feature_donors.domain.Donors
import com.example.nocountrydonation.util.ResultState

interface SearchRepository {
    suspend fun getSearch(query : String): ResultState<List<Donors>>
}