package com.lista.desafio04.core.data

import com.google.gson.annotations.SerializedName
import com.lista.desafio04.features.main.data.response.BookDetailsResponse
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
    var payload: ArrayList<BookDetailsResponse>
) : Serializable
