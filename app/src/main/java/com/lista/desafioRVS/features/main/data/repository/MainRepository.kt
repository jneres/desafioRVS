package com.lista.desafioRVS.features.main.data.repository

import com.lista.desafioRVS.core.data.BaseResponse
import com.lista.desafioRVS.core.network.ResponseWrapper
import com.lista.desafioRVS.features.main.data.response.BookDetailsResponse

interface MainRepository {
    suspend fun getBookList(apiKey: String): ResponseWrapper<BaseResponse<BookDetailsResponse>>
}