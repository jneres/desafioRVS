package com.lista.desafioRVS.features.main.ui.adapter

import androidx.recyclerview.widget.DiffUtil
import com.lista.desafioRVS.features.main.data.response.Book

class BooksDiffsCallBack(
    private val oldList: ArrayList<Book>,
    private val newList: ArrayList<Book>
) : DiffUtil.Callback() {

    override fun getOldListSize() = oldList.size

    override fun getNewListSize() = newList.size

    override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int) =
        oldList[oldItemPosition] == newList[newItemPosition]

    override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int) =
        oldList[oldItemPosition] == newList[newItemPosition]
}