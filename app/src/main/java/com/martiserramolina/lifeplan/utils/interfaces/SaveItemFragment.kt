package com.martiserramolina.lifeplan.utils.interfaces

import androidx.lifecycle.LifecycleOwner
import com.martiserramolina.lifeplan.viewmodels.interfaces.SaveItemViewModel

interface SaveItemFragment {

    fun SaveItemFragment.setupViews(funct: () -> Unit) { funct() }

    fun SaveItemFragment.setupWhenItemSavedFunctionality(
        viewModel: SaveItemViewModel,
        lifecycleOwner: LifecycleOwner,
        funct: () -> Unit
    ) {
        viewModel.itemSaved.observe(lifecycleOwner) { daySaved -> if (daySaved) funct() }
    }

    fun SaveItemFragment.onSaveMenuItemSelected(
        isItemValid: () -> Boolean,
        saveItem: () -> Unit,
        showMessageInvalidData: () -> Unit
    ): Boolean = saveItemIfValid(isItemValid, saveItem, showMessageInvalidData).run { true }

    private fun saveItemIfValid(
        isItemValid: () -> Boolean,
        saveItem: () -> Unit,
        showMessageInvalidData: () -> Unit
    ) {
        if (isItemValid()) saveItem() else showMessageInvalidData()
    }
}