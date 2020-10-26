package com.martiserramolina.lifeplan.ui.fragments.abstracts

import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import androidx.viewbinding.ViewBinding
import com.martiserramolina.lifeplan.ui.fragments.interfaces.OnDeleteMenuItemClickListener
import com.martiserramolina.lifeplan.ui.fragments.interfaces.OnEditMenuItemClickListener

abstract class ItemFragment<T : ViewBinding> :
    UpButtonFragment<T>(),
    OnEditMenuItemClickListener,
    OnDeleteMenuItemClickListener
{
    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(getMenuResource(), menu)
    }

    abstract fun getMenuResource(): Int

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            getEditMenuItemId() -> onEditMenuItemClicked()
            getDeleteMenuItemId() -> onDeleteMenuItemClicked()
            else -> super.onOptionsItemSelected(item)
        }
    }

    abstract fun getEditMenuItemId(): Int

    abstract fun getDeleteMenuItemId(): Int

    override fun onEditMenuItemClicked(): Boolean {
        navigateToEditItemFragment()
        return true
    }

    override fun onDeleteMenuItemClicked(): Boolean {
        deleteItem()
        return true
    }

    abstract fun navigateToEditItemFragment()

    abstract fun deleteItem()
}