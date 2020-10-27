package com.martiserramolina.lifeplan.ui.fragments.up


import android.os.Bundle
import android.view.MenuItem
import android.view.View
import androidx.activity.addCallback
import androidx.appcompat.widget.Toolbar
import androidx.viewbinding.ViewBinding
import com.martiserramolina.lifeplan.ui.activities.MainActivity
import com.martiserramolina.lifeplan.ui.fragments.BaseFragment

abstract class UpFragment<T : ViewBinding> : BaseFragment<T>() {

    private val mainActivity by lazy { activity as MainActivity }
    protected val navController by lazy { mainActivity.navController }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupActionBar()
        setupBackButton()
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            android.R.id.home -> {
                navigateToPreviousFragment()
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

    private fun setupActionBar() {
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
        mainActivity.onBackPressedDispatcher.addCallback(mainActivity) { navigateToPreviousFragment() }
    }

    abstract fun getToolbar(): Toolbar

    abstract fun getToolbarTitle(): String

    abstract fun navigateToPreviousFragment()
}