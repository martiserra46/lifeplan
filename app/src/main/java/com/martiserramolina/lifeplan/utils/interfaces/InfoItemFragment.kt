package com.martiserramolina.lifeplan.utils.interfaces

import androidx.lifecycle.LifecycleOwner
import com.martiserramolina.lifeplan.viewmodels.interfaces.DeleteItemViewModel

interface InfoItemFragment {

    fun InfoItemFragment.setupViews(funct: () -> Unit) { funct() }

    fun InfoItemFragment.setupWhenItemDeletedFunctionality(
        viewModel: DeleteItemViewModel,
        lifecycleOwner: LifecycleOwner,
        funct: () -> Unit
    ) {
        viewModel.itemDeleted.observe(lifecycleOwner) { itemDeleted -> if (itemDeleted) funct() }
    }

    fun InfoItemFragment.onEditMenuItemSelected(funct: () -> Unit): Boolean = funct().run { true }

    fun InfoItemFragment.onDeleteMenuItemSelected(funct: () -> Unit): Boolean = funct().run { true }
}