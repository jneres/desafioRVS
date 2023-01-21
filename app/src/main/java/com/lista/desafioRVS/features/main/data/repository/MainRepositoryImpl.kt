package com.lista.desafioRVS.features.main.data.repository

import com.lista.desafioRVS.core.data.BaseResponse
import com.lista.desafioRVS.core.network.ResponseWrapper
import com.lista.desafioRVS.core.network.apiCall
import com.lista.desafioRVS.features.main.data.MainService
import com.lista.desafioRVS.features.main.data.response.BookDetailsResponse

class MainRepositoryImpl(
    private val service: MainService
): MainRepository  {

    override suspend fun getBookList(apiKey: String): ResponseWrapper<BaseResponse<BookDetailsResponse>> {
        return apiCall {
            service.getBookList(apiKey)
        }
    }
}