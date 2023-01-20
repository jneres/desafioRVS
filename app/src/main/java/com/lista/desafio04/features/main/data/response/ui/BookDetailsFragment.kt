package com.lista.desafio04.features.main.data.response.ui

import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.lista.desafio04.R
import com.lista.desafio04.core.ui.BaseFragment
import com.lista.desafio04.core.utils.monetaryFormat
import com.lista.desafio04.databinding.FragmentBookDetailsBinding

class BookDetailsFragment : BaseFragment<FragmentBookDetailsBinding>() {

    private val args: BookDetailsFragmentArgs by navArgs()
    override fun layoutId() = R.layout.fragment_book_details

    override fun init() {
        navigation()
        setItems()
    }

    private fun navigation() {
        binding?.apply {
            imgBack.setOnClickListener {
                findNavController().popBackStack()
            }
        }
    }

    private fun setItems() {
        binding?.apply {
            val book = args.itemBook
            book?.let {
                txtTitle.text = it.title
                txtDescription.text = it.description.toString()
                txtAuthor.text = it.author.toString()
                txtPrice.text = it.price.toString().monetaryFormat()
            }
        }
    }
}