package com.lista.desafio04.features.main.viewModel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.lista.desafio04.BuildConfig
import com.lista.desafio04.core.network.ResponseWrapper
import com.lista.desafio04.features.main.data.repository.MainRepository
import com.lista.desafio04.features.main.data.response.Book
import kotlinx.coroutines.launch

class MainViewModel(private val repository: MainRepository) : ViewModel() {

    val getList: MutableLiveData<List<Book>> = MutableLiveData()
    private val booksList: ArrayList<Book> = arrayListOf()

    fun getBookList() {
        viewModelScope.launch {

            when (val result = repository.getBookList(BuildConfig.APIKEY)) {
                is ResponseWrapper.Success -> {
                    val books = result.value.payload.map { it.bookDetails.get(0) }
                    getList.value = books

                    booksList.addAll(books)
                }
                is ResponseWrapper.Error -> {}
            }
        }
    }

    fun searchBook(search: String) {
        getList.value = if (search.isEmpty()) {
            booksList
        } else {
            booksList.filter { it.title.contains(search, true) }
        }
    }
}
