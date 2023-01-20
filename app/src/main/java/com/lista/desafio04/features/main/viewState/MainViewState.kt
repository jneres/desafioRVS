package com.lista.desafio04.features.main.viewState

sealed class MainViewState {
    data class ShowLoading(val show: Boolean): MainViewState()
    data class Error(val message: String): MainViewState()
}
