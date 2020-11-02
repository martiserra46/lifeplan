package com.martiserramolina.lifeplan.extensions

import androidx.core.content.ContextCompat
import androidx.lifecycle.LifecycleOwner
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.viewbinding.ViewBinding
import com.martiserramolina.lifeplan.R
import com.martiserramolina.lifeplan.adapters.recyclerview.adapters.ItemAdapter
import com.martiserramolina.lifeplan.adapters.recyclerview.diffutils.ItemListDiffCallback
import com.martiserramolina.lifeplan.adapters.recyclerview.viewholders.ItemViewHolder
import com.martiserramolina.lifeplan.viewmodels.capable_of_fetching_items.CapableOfFetchingItemsI

fun <T: ItemViewHolder<out ViewBinding, U>, S: ItemListDiffCallback<U>, U> RecyclerView
        .setupAutoLoadItemsFunctionality(
    lifecycleOwner: LifecycleOwner,
    itemAdapter: ItemAdapter<T, S, U>,
    capableOfFetchingItems: CapableOfFetchingItemsI<U>
) {
    val linearLayoutManager = LinearLayoutManager(context)
    layoutManager = linearLayoutManager
    adapter = itemAdapter
    setHasFixedSize(true)
    addItemDecoration(
        DividerItemDecoration(context, DividerItemDecoration.VERTICAL).apply {
            setDrawable(ContextCompat.getDrawable(context, R.drawable.div_rvi)!!)
        }
    )
    addOnScrollListener(object : RecyclerView.OnScrollListener() {
        override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
            if (linearLayoutManager.findLastVisibleItemPosition() == itemAdapter.itemCount)
                capableOfFetchingItems.fetchItemsIfNotFetched((itemAdapter.itemCount + 1).toLong())
        }
    })
    capableOfFetchingItems.itemsFetched.observe(lifecycleOwner) { itemAdapter.items = it }
}