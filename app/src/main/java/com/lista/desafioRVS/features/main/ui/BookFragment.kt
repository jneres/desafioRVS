package com.lista.desafioRVS.features.main.ui

import android.os.Bundle
import android.widget.Toast
import androidx.core.widget.addTextChangedListener
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.lista.desafioRVS.R
import com.lista.desafioRVS.core.ui.BaseFragment
import com.lista.desafioRVS.databinding.FragmentBookBinding
import com.lista.desafioRVS.features.main.data.response.Book
import com.lista.desafioRVS.features.main.ui.adapter.BookListAdapter
import com.lista.desafioRVS.features.main.viewModel.MainViewModel
import com.lista.desafioRVS.features.main.viewState.MainViewState
import org.koin.androidx.viewmodel.ext.android.viewModel

class BookFragment : BaseFragment<FragmentBookBinding>() {

    private val viewModel: MainViewModel by viewModel()
    private lateinit var adapter: BookListAdapter

    override fun layoutId() = R.layout.fragment_book

    override fun create() {
        super.create()
        viewModel.getBookList()
    }

    override fun init() {
        initRecyclerview()
        setObserver()
        setupSearch()
    }

    private fun setObserver() {
        viewModel.getList.observe(viewLifecycleOwner) { itemBook ->
            itemBook?.let {
                adapter.updateBooks(ArrayList(it))
            }
            binding?.listBookAdapter?.scrollToPosition(0)
        }

        viewModel.viewState.observe(viewLifecycleOwner) {
            when (it) {
                is MainViewState.Error -> {
                    Toast.makeText(requireContext(), it.message, Toast.LENGTH_LONG).show()
                }
                is MainViewState.ShowLoading -> showLoading(it.show)

                else -> Unit
            }
        }
    }

    private fun initRecyclerview() {
        this.adapter = BookListAdapter {
            onBookSelected(it)
        }

        binding.apply {
            this?.listBookAdapter?.adapter = adapter
            this?.listBookAdapter?.layoutManager = LinearLayoutManager(requireContext())
        }
    }

    private fun onBookSelected(item: Book) {

        val bundle = Bundle().apply {
            putSerializable("itemBook", item)
        }

        findNavController().navigate(R.id.bookDetailsFragment, bundle)
    }

    private fun setupSearch() {
        binding?.apply {
            editSearch.addTextChangedListener {
                viewModel.searchBook(it.toString())
            }
        }
    }
}