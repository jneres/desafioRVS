package com.lista.desafioRVS.features.main.viewModel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.lista.desafioRVS.BuildConfig
import com.lista.desafioRVS.core.network.ResponseWrapper
import com.lista.desafioRVS.features.main.data.repository.MainRepository
import com.lista.desafioRVS.features.main.data.response.Book
import com.lista.desafioRVS.features.main.viewState.MainViewState
import kotlinx.coroutines.launch

class MainViewModel(private val repository: MainRepository) : ViewModel() {

    val viewState: MutableLiveData<MainViewState> = MutableLiveData()
    val getList: MutableLiveData<List<Book>> = MutableLiveData()
    private val booksList: ArrayList<Book> = arrayListOf()

    fun getBookList() {
        viewModelScope.launch {
            viewState.value = MainViewState.ShowLoading(true)

            when (val result = repository.getBookList(BuildConfig.APIKEY)) {
                is ResponseWrapper.Success -> {
                    val books = result.value.payload.map { it.bookDetails.get(0) }
                    getList.value = books

                    booksList.addAll(books)
                    viewState.value = MainViewState.ShowLoading(false)
                }
                is ResponseWrapper.Error -> {
                    viewState.value = MainViewState.ShowLoading(false)
                    viewState.value = MainViewState.Error(result.error)
                }
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
