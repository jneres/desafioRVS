package com.lista.desafioRVS.features.main.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.lista.desafioRVS.databinding.ItemListBookBinding
import com.lista.desafioRVS.features.main.data.response.Book

class BookListAdapter(var callback: (Book) -> Unit) :
    RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private var books: ArrayList<Book> = ArrayList()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return BookViewHolder(
            ItemListBookBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        )
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when (holder) {
            is BookViewHolder -> {
                holder.bind(books[position])
                holder.itemView.setOnClickListener {
                    callback.invoke(books[position])
                }
            }
        }
    }

    override fun getItemCount() = books.size

    fun updateBooks(books: ArrayList<Book>) {
        val diffCallback = BooksDiffsCallBack(this.books, books)
        val diffResult = DiffUtil.calculateDiff(diffCallback)

        this.books.clear()
        this.books.addAll(books)
        diffResult.dispatchUpdatesTo(this)
    }

    class BookViewHolder(
        val binding: ItemListBookBinding
    ) : RecyclerView.ViewHolder(binding.root) {

        fun bind(item: Book) {

            binding.apply {
                txtTitle.text = item.title
                txtAuthor.text = item.author
                txtPrice.text = item.price
            }
        }
    }

}
