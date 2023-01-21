package com.lista.desafioRVS.core.network

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import retrofit2.HttpException

const val ERROR_REQUEST = "Erro ao efetuar requisição"

suspend inline fun <reified T> apiCall(crossinline apiCall: suspend () -> T): ResponseWrapper<T> {

    return withContext(Dispatchers.IO) {
        try {
            ResponseWrapper.Success(apiCall.invoke())
        } catch (throwable: Throwable) {
            when (throwable) {
                is HttpException -> {
                    val code = throwable.code()
                    ResponseWrapper.Error(code, ERROR_REQUEST)
                }
                else -> {
                    ResponseWrapper.Error(null, ERROR_REQUEST)
                }
            }
        }
    }
}

