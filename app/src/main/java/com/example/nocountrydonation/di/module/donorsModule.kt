package com.example.nocountrydonation.di.module

import org.koin.core.module.dsl.factoryOf
import org.koin.dsl.module
import com.example.nocountrydonation.feature_donors.data.DonorsRepositoryImp
import com.example.nocountrydonation.feature_donors.domain.DonorsRepository
import com.example.nocountrydonation.feature_donors.data.network.DonorService
import com.example.nocountrydonation.feature_donors.presentation.DonorsViewModel
import com.example.nocountrydonation.feature_search.domain.SearchRepository
import com.example.nocountrydonation.feature_search.data.SearchRepositoryImp
import com.example.nocountrydonation.feature_search.data.network.SearchService
import com.example.nocountrydonation.feature_search.presentation.SearchViewModel
import org.koin.androidx.viewmodel.dsl.viewModelOf
import org.koin.core.module.dsl.bind

val donorsModule = module {

    factoryOf(::DonorsRepositoryImp){bind<DonorsRepository>()}
    factoryOf(::DonorService)
    viewModelOf(::DonorsViewModel)

    factoryOf(::SearchRepositoryImp){bind<SearchRepository>()}
    factoryOf(::SearchService)
    viewModelOf(::SearchViewModel)

}