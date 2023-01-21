package com.lista.desafioRVS.features.main.data.response

import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class BookDetailsResponse(
    @SerializedName("book_details")
    val bookDetails: List<Book>
) : Serializable


data class Book(

    @SerializedName("title")
    val title: String = "",

    @SerializedName("description")
    val description: String? = "",

    @SerializedName("author")
    val author: String? = "",

    @SerializedName("price")
    val price: String? = ""

) : Serializable
