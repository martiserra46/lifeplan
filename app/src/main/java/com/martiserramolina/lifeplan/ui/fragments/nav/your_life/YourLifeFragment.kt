package com.martiserramolina.lifeplan.ui.fragments.nav.your_life

import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.martiserramolina.lifeplan.R
import com.martiserramolina.lifeplan.databinding.FragmentNavYourLifeBinding
import com.martiserramolina.lifeplan.ui.activities.MainActivity
import com.martiserramolina.lifeplan.ui.fragments.FragmentWithBinding
import com.martiserramolina.lifeplan.ui.fragments.MainFragmentDirections
import com.martiserramolina.lifeplan.viewmodels.your_life.YourLifeViewModel

class YourLifeFragment : FragmentWithBinding<FragmentNavYourLifeBinding>() {

    private val mainActivity by lazy { activity as MainActivity }

    private val viewModel by lazy {
        ViewModelProvider(
            this, YourLifeViewModel.Factory(requireActivity().application)
        ).get(YourLifeViewModel::class.java)
    }

    override fun getBinding(
        inflater: LayoutInflater, container: ViewGroup?
    ): FragmentNavYourLifeBinding {
        return FragmentNavYourLifeBinding.inflate(inflater, container, false)
    }

    override fun getRootView(): View = binding.root

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setHasOptionsMenu(true)
        setupDescription()
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.your_life_menu, menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.your_life_edit_mi -> {
                navigateToEditYourLifeFragment()
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

    private fun setupDescription() {
        viewModel.yourLife.observe(viewLifecycleOwner) { yourLife ->
            yourLife?.let { binding.fragmentNavYourLifeDescriptionTv.text = yourLife.text }
        }
    }

    private fun navigateToEditYourLifeFragment() {
        mainActivity.navController
            .navigate(MainFragmentDirections.actionMainFragmentToEditYourLifeFragment())
    }
}