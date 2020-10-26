package com.martiserramolina.lifeplan.ui.fragments.abstracts

import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import androidx.viewbinding.ViewBinding

abstract class SaveItemFragment<T : ViewBinding> : UpButtonFragment<T>() {

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(getMenuResource(), menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            getSaveMenuItemId() -> {
                saveItemDataIfValid()
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

    abstract fun getMenuResource(): Int

    abstract fun getSaveMenuItemId(): Int

    private fun saveItemDataIfValid() {
        if (isItemDataValid()) saveItemData()
        else showInvalidDataMessage()
    }

    abstract fun isItemDataValid(): Boolean

    abstract fun saveItemData()

    abstract fun showInvalidDataMessage()
}