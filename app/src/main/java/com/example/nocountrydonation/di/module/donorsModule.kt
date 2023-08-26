package com.example.nocountrydonation.di.module

import org.koin.core.module.dsl.factoryOf
import org.koin.dsl.module
import com.example.nocountrydonation.feature_donors.data.DonorsRepositoryImp
import com.example.nocountrydonation.feature_donors.domain.DonorsRepository
import com.example.nocountrydonation.feature_donors.data.network.DonorService
import com.example.nocountrydonation.feature_donors.presentation.DonorsViewModel
import org.koin.androidx.viewmodel.dsl.viewModelOf
import org.koin.core.module.dsl.bind

val donorsModule = module {

    factoryOf(::DonorsRepositoryImp){bind<DonorsRepository>()}
    factoryOf(::DonorService)
    viewModelOf(::DonorsViewModel)

}