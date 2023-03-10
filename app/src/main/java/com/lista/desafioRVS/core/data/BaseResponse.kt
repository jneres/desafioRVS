package com.lista.desafioRVS.core.data

import com.google.gson.annotations.SerializedName
import com.lista.desafioRVS.features.main.data.response.BookDetailsResponse
import java.io.Serializable

data class BaseResponse<T>(

    @SerializedName("status")
    val status: String = "",

    @SerializedName("copyright")
    val copyright: String = "",

    @SerializedName("num_results")
    val num_results: Int = 0,

    @SerializedName("last_modified")
    val last_modified: String = "",

    @SerializedName("results")
    val payload: ArrayList<BookDetailsResponse> = arrayListOf()
) : Serializable
