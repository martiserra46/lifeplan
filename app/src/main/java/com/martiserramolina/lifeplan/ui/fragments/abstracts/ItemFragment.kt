package com.martiserramolina.lifeplan.ui.fragments.abstracts

import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import androidx.viewbinding.ViewBinding

abstract class ItemFragment<T : ViewBinding> : SecondaryFragment<T>() {

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(getMenuResource(), menu)
    }

    abstract fun getMenuResource(): Int

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            getEditMenuItemId() -> {
                navigateToEditItemFragment()
                true
            }
            getDeleteMenuItemId() -> {
                deleteItem()
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

    abstract fun getEditMenuItemId(): Int

    abstract fun getDeleteMenuItemId(): Int

    abstract fun navigateToEditItemFragment()

    abstract fun deleteItem()
}