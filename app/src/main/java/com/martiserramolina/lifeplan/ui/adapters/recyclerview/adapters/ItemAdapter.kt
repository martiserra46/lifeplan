package com.martiserramolina.lifeplan.ui.adapters.recyclerview.adapters

import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import androidx.viewbinding.ViewBinding
import com.martiserramolina.lifeplan.ui.adapters.recyclerview.diffutils.ItemListDiffCallback
import com.martiserramolina.lifeplan.ui.adapters.recyclerview.viewholders.ItemViewHolder

abstract class ItemAdapter<T: ItemViewHolder<out ViewBinding, U>, S: ItemListDiffCallback<U>, U>(
    protected val onItemClick: (U) -> Unit
) : RecyclerView.Adapter<T>() {

    var items = emptyList<U>()
        set(value) {
            val oldValue = field
            field = value.toList()
            DiffUtil.calculateDiff(buildItemListDiffCallback(oldValue, field))
                .dispatchUpdatesTo(this)
        }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): T {
        return buildViewHolder(parent, viewType)
    }

    override fun onBindViewHolder(holder: T, position: Int) {
        holder.bind(items[position])
    }

    override fun getItemCount(): Int = items.size

    abstract fun buildViewHolder(parent: ViewGroup, viewType: Int): T

    abstract fun buildItemListDiffCallback(oldList: List<U>, newList: List<U>): S
}