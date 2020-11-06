package com.martiserramolina.lifeplan.ui.adapters.recyclerview.adapters

import androidx.paging.PagedListAdapter
import androidx.viewbinding.ViewBinding
import com.martiserramolina.lifeplan.ui.adapters.recyclerview.diffutils.ItemDiffCallback
import com.martiserramolina.lifeplan.ui.adapters.recyclerview.viewholders.ItemViewHolder

abstract class ItemAdapter<T, S: ItemViewHolder<out ViewBinding, T>, U: ItemDiffCallback<T>>(
    itemDiffCallback: U
) : PagedListAdapter<T, S>(itemDiffCallback) {
    override fun onBindViewHolder(holder: S, position: Int) {
        val item: T? = getItem(position)
        if (item != null) holder.bind(item)
    }
}