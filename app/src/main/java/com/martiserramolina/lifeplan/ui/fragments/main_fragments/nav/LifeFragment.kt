package com.martiserramolina.lifeplan.ui.fragments.main_fragments.nav

import android.os.Bundle
import android.view.*
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import com.martiserramolina.lifeplan.R
import com.martiserramolina.lifeplan.databinding.FragmentNavLifeBinding
import com.martiserramolina.lifeplan.ui.activities.MainActivity
import com.martiserramolina.lifeplan.ui.fragments.BaseFragment
import com.martiserramolina.lifeplan.ui.fragments.main_fragments.MainFragmentDirections
import com.martiserramolina.lifeplan.viewmodels.life.LifeViewModel

class LifeFragment : BaseFragment<FragmentNavLifeBinding>() {

    private val mainActivity by lazy { activity as MainActivity }

    private val viewModel by lazy {
        ViewModelProvider(
            this, LifeViewModel.Factory(requireActivity().application)
        ).get(LifeViewModel::class.java)
    }

    override fun getBinding(
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
            R.id.life_edit_mi -> {
                if (isDataLoaded()) navigateToEditLifeFragment()
                else showWaitUntilDataIsLoadedMessage()
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

    private fun setupTextTv() {
        viewModel.life.observe(viewLifecycleOwner) { life ->
            life?.let { binding.fragmentNavLifeDescriptionTv.text = life.lifeText }
        }
    }

    private fun isDataLoaded(): Boolean = viewModel.life.value != null

    private fun navigateToEditLifeFragment() {
        mainActivity.navController.navigate(
            MainFragmentDirections.actionMainFragmentToEditLifeFragment(viewModel.life.value)
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