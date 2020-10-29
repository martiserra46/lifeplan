package com.martiserramolina.lifeplan.ui.fragments.up


import android.os.Bundle
import android.view.MenuItem
import android.view.View
import androidx.activity.addCallback
import androidx.appcompat.widget.Toolbar
import androidx.viewbinding.ViewBinding
import com.martiserramolina.lifeplan.ui.fragments.BaseFragment

abstract class UpFragment<T : ViewBinding> : BaseFragment<T>() {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupToolbar()
        setupBackButton()
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            android.R.id.home -> onUpButtonClicked()
            else -> super.onOptionsItemSelected(item)
        }
    }

    private fun setupToolbar() {
        mainActivity.apply {
            setSupportActionBar(getToolbar())
            supportActionBar?.apply {
                title = getToolbarTitle()
                setDisplayHomeAsUpEnabled(true)
            }
        }
        setHasOptionsMenu(true)
    }

    private fun setupBackButton() {
        mainActivity.onBackPressedDispatcher
            .addCallback(mainActivity) { navigateToPreviousFragment() }
    }

    private fun onUpButtonClicked(): Boolean {
        navigateToPreviousFragment()
        return true
    }

    abstract fun getToolbar(): Toolbar

    open fun getToolbarTitle(): String = ""

    abstract fun navigateToPreviousFragment()
}