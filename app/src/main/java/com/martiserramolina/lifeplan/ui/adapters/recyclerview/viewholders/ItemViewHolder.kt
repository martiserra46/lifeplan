package com.martiserramolina.lifeplan.ui.adapters.recyclerview.viewholders

import androidx.recyclerview.widget.RecyclerView
import androidx.viewbinding.ViewBinding

abstract class ItemViewHolder<T: ViewBinding, U>(
    protected val binding: T,
    private val onItemClick: (U) -> Unit
) : RecyclerView.ViewHolder(binding.root) {

    fun bind(item: U) {
        bindItemData(item)
        itemView.setOnClickListener { onItemClick(item) }
    }

    protected abstract fun bindItemData(item: U)
}