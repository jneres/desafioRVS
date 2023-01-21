package com.lista.desafioRVS.features.main.di

import com.lista.desafioRVS.core.network.RetrofitConfig
import com.lista.desafioRVS.features.main.data.MainService
import com.lista.desafioRVS.features.main.data.repository.MainRepository
import com.lista.desafioRVS.features.main.data.repository.MainRepositoryImpl
import com.lista.desafioRVS.features.main.viewModel.MainViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module


val bookModule = module {

    single<MainService> { RetrofitConfig.getApiService() }

    single<MainRepository> { MainRepositoryImpl(get()) }

    viewModel { MainViewModel(get()) }
}