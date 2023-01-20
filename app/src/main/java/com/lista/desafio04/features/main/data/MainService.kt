package com.lista.desafio04.features.main.data

import com.lista.desafio04.core.data.BaseResponse
import com.lista.desafio04.features.main.data.response.BookDetailsResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface MainService {
    @GET("svc/books/v3/lists.json?list=paperback-nonfiction")
    suspend fun getBookList(@Query("api-key") apiKey: String): BaseResponse<BookDetailsResponse>
}