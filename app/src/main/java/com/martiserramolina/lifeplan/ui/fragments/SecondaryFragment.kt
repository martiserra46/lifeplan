package com.martiserramolina.lifeplan.ui.fragments


import android.os.Bundle
import android.view.LayoutInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import androidx.activity.addCallback
import androidx.appcompat.widget.Toolbar
import androidx.fragment.app.Fragment
import androidx.navigation.Navigation
import androidx.viewbinding.ViewBinding
import com.martiserramolina.lifeplan.databinding.FragmentNavYourLifeSaveBinding
import com.martiserramolina.lifeplan.ui.activities.MainActivity

abstract class SecondaryFragment<T : ViewBinding> : FragmentWithBinding<T>() {

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
                title = getString(getTitleId())
                setDisplayHomeAsUpEnabled(true)
            }
        }
        setHasOptionsMenu(true)
    }

    private fun setupBackButton() {
        mainActivity.onBackPressedDispatcher.addCallback(mainActivity) { navigateToPreviousFragment() }
    }

    abstract fun getToolbar(): Toolbar

    abstract fun getTitleId(): Int

    abstract fun navigateToPreviousFragment()
}