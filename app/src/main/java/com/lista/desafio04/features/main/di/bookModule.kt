package com.lista.desafio04.features.main.di

import com.lista.desafio04.core.network.RetrofitConfig
import com.lista.desafio04.features.main.data.MainService
import com.lista.desafio04.features.main.data.repository.MainRepository
import com.lista.desafio04.features.main.viewModel.MainViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module


val bookModule = module {

    single<MainService> { RetrofitConfig.getApiService() }

    single { MainRepository(get()) }

    viewModel { MainViewModel(get()) }
}