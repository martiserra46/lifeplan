package com.martiserramolina.lifeplan.ui.fragments.nav.life

import android.os.Bundle
import android.view.*
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import com.martiserramolina.lifeplan.R
import com.martiserramolina.lifeplan.databinding.FragmentNavLifeBinding
import com.martiserramolina.lifeplan.ui.activities.MainActivity
import com.martiserramolina.lifeplan.ui.fragments.MainFragmentDirections
import com.martiserramolina.lifeplan.ui.fragments.interfaces.OnEditMenuItemClickListener
import com.martiserramolina.lifeplan.ui.fragments.nav.NavFragment
import com.martiserramolina.lifeplan.viewmodels.viewmodels.life.info.InfoLifeViewModel

class NavLifeFragment : NavFragment<FragmentNavLifeBinding>(), OnEditMenuItemClickListener {

    private val mainActivity by lazy { activity as MainActivity }

    private val viewModel by lazy {
        ViewModelProvider(
            this, InfoLifeViewModel.Factory(requireActivity().application)
        ).get(InfoLifeViewModel::class.java)
    }

    override fun buildBinding(
        inflater: LayoutInflater, container: ViewGroup?
    ): FragmentNavLifeBinding {
        return FragmentNavLifeBinding.inflate(inflater, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setHasOptionsMenu(true)
        setupTextTv()
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.life_menu, menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.life_edit_mi -> onEditMenuItemClicked()
            else -> super.onOptionsItemSelected(item)
        }
    }

    private fun setupTextTv() {
        viewModel.life.observe(viewLifecycleOwner) { life ->
            binding.fragmentNavLifeDescriptionTv.text = life.lifeText
        }
    }

    override fun onEditMenuItemClicked(): Boolean {
        if (isDataLoaded()) navigateToEditLifeFragment()
        else showWaitUntilDataIsLoadedMessage()
        return true
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
            "Wait until all the data is loaded",
            Toast.LENGTH_SHORT
        ).show()
    }
}