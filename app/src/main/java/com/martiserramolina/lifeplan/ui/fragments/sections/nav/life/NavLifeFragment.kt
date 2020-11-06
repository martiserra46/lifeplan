package com.martiserramolina.lifeplan.ui.fragments.sections.nav.life

import android.os.Bundle
import android.view.*
import android.widget.Toast
import com.martiserramolina.lifeplan.R
import com.martiserramolina.lifeplan.databinding.FragmentNavLifeBinding
import com.martiserramolina.lifeplan.ui.dialogs.DeleteItemDialogFragment
import com.martiserramolina.lifeplan.ui.fragments.main.MainFragmentDirections
import com.martiserramolina.lifeplan.ui.fragments.sections.nav.NavFragment
import com.martiserramolina.lifeplan.viewmodels.factory.ViewModelFactory
import com.martiserramolina.lifeplan.viewmodels.viewmodels.sections.life.info.InfoLifeViewModel

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
        setupDescription()
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.life_menu, menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.life_edit_mi -> onEditMenuItemSelected()
            R.id.life_delete_mi -> onDeleteMenuItemSelected()
            else -> super.onOptionsItemSelected(item)
        }
    }

    private fun setupDescription() {
        viewModel.life.observe(viewLifecycleOwner) { life ->
            if (life == null) return@observe
            if (life.lifeText.isEmpty()) {
                binding.apply {
                    fragmentNavLifeEmptyCl.visibility = View.VISIBLE
                    fragmentNavLifeTextSv.visibility = View.GONE
                }
            } else {
                binding.fragmentNavLifeEmptyTv.text = life.lifeText
            }
        }
    }

    private fun onEditMenuItemSelected(): Boolean =
        navigateToEditLifeFragmentIfDataIsLoaded().run { true }

    private fun navigateToEditLifeFragmentIfDataIsLoaded() {
        if (isDataLoaded()) {
            navigateToEditLifeFragment()
        } else {
            showWaitUntilDataIsLoadedMessage()
        }
    }

    private fun onDeleteMenuItemSelected(): Boolean = deleteLife().run { true }

    private fun isDataLoaded(): Boolean = viewModel.life.value != null

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

    private fun deleteLife() {
        DeleteItemDialogFragment(
            R.string.dialog_message_delete_life,
            { viewModel.deleteItem() }
        ).show(parentFragmentManager, getString(R.string.dialog_message_delete_life))
    }
}