package com.lista.desafioRVS.feature.main.viewModel

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.lista.desafioRVS.core.data.BaseResponse
import com.lista.desafioRVS.core.network.ResponseWrapper
import com.lista.desafioRVS.features.main.data.repository.MainRepository
import com.lista.desafioRVS.features.main.data.response.Book
import com.lista.desafioRVS.features.main.data.response.BookDetailsResponse
import com.lista.desafioRVS.features.main.viewModel.MainViewModel
import com.lista.desafioRVS.features.main.viewState.MainViewState
import com.lista.desafioRVS.util.TestCoroutineRule
import kotlinx.coroutines.ExperimentalCoroutinesApi
import org.junit.Rule
import org.junit.Test
import kotlin.test.assertEquals
import kotlin.test.assertTrue

@OptIn(ExperimentalCoroutinesApi::class)
class MainViewModelTest {

    private lateinit var viewModel: MainViewModel

    @get:Rule
    val instantExecutorRule = InstantTaskExecutorRule()

    @get:Rule
    val testCoroutineRule = TestCoroutineRule()

    @Test
    fun `Given call get books list with repository success should update correct live data`() {

        val (book, result) = getBookResponseMock()

        viewModel = MainViewModel(MainRepositoryMock(true, result))
        viewModel.getBookList()

        assertEquals(book, viewModel.getList.value?.get(0))
    }

    @Test
    fun `Given call get books list with repository success should update correct live`() {

        val (book, result) = getBookResponseMock()

        viewModel = MainViewModel(MainRepositoryMock(true, result))
        viewModel.getBookList()
        viewModel.searchBook("Title1")

        assertEquals(book, viewModel.getList.value?.get(0))
    }

    @Test
    fun `Given call get empty book list with repository success should update full list`() {

        val (book, result) = getBookResponseMock()

        viewModel = MainViewModel(MainRepositoryMock(true, result))
        viewModel.getBookList()
        viewModel.searchBook("")

        assertEquals(book, viewModel.getList.value?.get(0))
    }

    @Test
    fun `Given the call, getting the list of books with repository failure should return error data`() {

        viewModel = MainViewModel(MainRepositoryMock(false))
        viewModel.getBookList()

        assertTrue(viewModel.viewState.value is MainViewState.Error)
    }

    private fun getBookResponseMock(): Pair<Book, ArrayList<BookDetailsResponse>> {
        val book1 = Book("Title1")
        val book2 = Book("Test")
        val listBook = arrayListOf(book1, book2)
        val result = arrayListOf(
            BookDetailsResponse(listBook)
        )
        return Pair(book1, result)
    }
}

class MainRepositoryMock(val isSuccess: Boolean, val list: ArrayList<BookDetailsResponse> = arrayListOf()) :
    MainRepository {

    override suspend fun getBookList(apiKey: String): ResponseWrapper<BaseResponse<BookDetailsResponse>> {

        if (isSuccess) {
            return ResponseWrapper.Success(BaseResponse(payload = list))
        }

        return ResponseWrapper.Error()
    }
}