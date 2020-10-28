package com.martiserramolina.lifeplan.ui.fragments.nav.life

import android.os.Bundle
import android.view.*
import android.widget.Toast
import com.martiserramolina.lifeplan.R
import com.martiserramolina.lifeplan.databinding.FragmentNavLifeBinding
import com.martiserramolina.lifeplan.ui.fragments.MainFragmentDirections
import com.martiserramolina.lifeplan.ui.fragments.nav.NavFragment
import com.martiserramolina.lifeplan.viewmodels.factory.ViewModelFactory
import com.martiserramolina.lifeplan.viewmodels.viewmodels.life.info.InfoLifeViewModel

class NavLifeFragment : NavFragment<FragmentNavLifeBinding>() {

    private val viewModel by ViewModelFactory.Delegate(
        this, InfoLifeViewModel::class.java
    ) { InfoLifeViewModel(mainActivity.application) }

    override fun buildBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ): FragmentNavLifeBinding = FragmentNavLifeBinding.inflate(
        inflater, container, false
    )

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setHasOptionsMenu(true)
        setupTextTextView()
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.life_menu, menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.life_edit_mi -> onEditMenuItemSelected()
            else -> super.onOptionsItemSelected(item)
        }
    }

    private fun setupTextTextView() {
        viewModel.life.observe(viewLifecycleOwner) { life ->
            binding.fragmentNavLifeDescriptionTv.text = life.lifeText
        }
    }

    private fun onEditMenuItemSelected(): Boolean {
        navigateToEditLifeFragmentIfDataIsLoaded()
        return true
    }

    private fun navigateToEditLifeFragmentIfDataIsLoaded() {
        if (isDataLoaded()) {
            navigateToEditLifeFragment()
        } else {
            showWaitUntilDataIsLoadedMessage()
        }
    }

    private fun isDataLoaded(): Boolean = viewModel.isLifeLoaded.value == true

    private fun navigateToEditLifeFragment() {
        mainActivity.navController.navigate(
            MainFragmentDirections.actionMainFragmentToEditLifeFragment(viewModel.life.value!!)
        )
    }

    private fun showWaitUntilDataIsLoadedMessage() {
        Toast.makeText(
            context,
            getString(R.string.wait_until_all_life_data_is_loaded),
            Toast.LENGTH_SHORT
        ).show()
    }
}