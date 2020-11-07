package com.martiserramolina.lifeplan.utils.interfaces

import android.view.View
import androidx.core.content.ContextCompat
import androidx.lifecycle.LifecycleOwner
import androidx.paging.PagedListAdapter
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.RecyclerView
import com.martiserramolina.lifeplan.R
import com.martiserramolina.lifeplan.viewmodels.interfaces.LoadItemsViewModel

interface LoadItemsFragment {

    fun <T, U: RecyclerView.ViewHolder> LoadItemsFragment.setupItemsRecyclerView(
        recyclerView: RecyclerView,
        adapter: PagedListAdapter<T, U>,
        viewModel: LoadItemsViewModel<T>,
        lifecycleOwner: LifecycleOwner,
        emptySection: View
    ) {
        recyclerView.apply {
            this.adapter = adapter
            addItemDecoration(
                DividerItemDecoration(context, DividerItemDecoration.VERTICAL).apply {
                    setDrawable(ContextCompat.getDrawable(context, R.drawable.div_rvi)!!)
                }
            )
        }
        viewModel.items.observe(lifecycleOwner) { items ->
            if (items.isEmpty()) {
                emptySection.visibility = View.VISIBLE
                recyclerView.visibility = View.GONE
            } else {
                adapter.submitList(items)
            }
        }
    }

    fun LoadItemsFragment.onAddMenuItemSelected(funct: () -> Unit) = funct().run { true }
}