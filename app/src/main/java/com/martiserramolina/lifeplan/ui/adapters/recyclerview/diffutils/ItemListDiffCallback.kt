package com.martiserramolina.lifeplan.ui.adapters.recyclerview.diffutils

import androidx.recyclerview.widget.DiffUtil

abstract class ItemListDiffCallback<T>(
    private val oldList: List<T>,
    private val newList: List<T>
) : DiffUtil.Callback() {
    override fun getOldListSize() = oldList.size
    override fun getNewListSize() = newList.size
    override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int) =
        getItemId(oldList[oldItemPosition]) == getItemId(newList[newItemPosition])
    override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int) =
        oldList[oldItemPosition] == newList[newItemPosition]
    abstract fun getItemId(item: T): Long
}