package com.martiserramolina.lifeplan.ui.fragments.nav.situation

import android.os.Bundle
import android.view.*
import androidx.lifecycle.ViewModelProvider
import com.martiserramolina.lifeplan.R
import com.martiserramolina.lifeplan.databinding.FragmentNavSituationBinding
import com.martiserramolina.lifeplan.ui.activities.MainActivity
import com.martiserramolina.lifeplan.ui.fragments.FragmentWithBinding
import com.martiserramolina.lifeplan.ui.adapters.SituationDayAdapter
import com.martiserramolina.lifeplan.ui.fragments.MainFragmentDirections
import com.martiserramolina.lifeplan.viewmodels.situation.SituationViewModel

class SituationFragment : FragmentWithBinding<FragmentNavSituationBinding>() {

    private val mainActivity by lazy { activity as MainActivity }

    private val viewModel by lazy {
        ViewModelProvider(
            this, SituationViewModel.Factory(requireActivity().application)
        ).get(SituationViewModel::class.java)
    }

    override fun getBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ): FragmentNavSituationBinding {
        return FragmentNavSituationBinding.inflate(inflater, container, false)
    }

    override fun getRootView(): View = binding.root

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setHasOptionsMenu(true)
        setupRecyclerView()
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.situation_menu, menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.situation_add_mi -> {
                navigateToAddSituationDayFragment()
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

    private fun setupRecyclerView() {
        binding.fragmentNavSituationRv.adapter = SituationDayAdapter()
        viewModel.situationDays.observe(viewLifecycleOwner) { situationDays ->
            binding.fragmentNavSituationRv.adapter
                .run { this as SituationDayAdapter }.listSituationDays = situationDays
        }
    }

    private fun navigateToAddSituationDayFragment() {
        mainActivity.navController
            .navigate(MainFragmentDirections.actionMainFragmentToAddSituationDayFragment())
    }
}