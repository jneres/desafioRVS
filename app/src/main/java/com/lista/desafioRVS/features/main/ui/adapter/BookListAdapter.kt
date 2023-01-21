package com.lista.desafioRVS.features.main.ui.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
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

    @SuppressLint("NotifyDataSetChanged")
    fun updateBooks(books: ArrayList<Book>) {
        this.books = books
        notifyDataSetChanged()
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
