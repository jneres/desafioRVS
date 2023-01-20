package com.lista.desafio04.features.main.data.repository

import com.lista.desafio04.core.data.BaseResponse
import com.lista.desafio04.core.network.ResponseWrapper
import com.lista.desafio04.core.network.apiCall
import com.lista.desafio04.features.main.data.MainService
import com.lista.desafio04.features.main.data.response.BookDetailsResponse

class MainRepository(
    private val service: MainService
) {
    suspend fun getBookList(apiKey: String): ResponseWrapper<BaseResponse<BookDetailsResponse>> {
        return apiCall {
            service.getBookList(apiKey)
        }
    }
}