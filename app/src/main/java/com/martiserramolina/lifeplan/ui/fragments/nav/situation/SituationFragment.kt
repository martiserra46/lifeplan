package com.martiserramolina.lifeplan.ui.fragments.nav.situation

import android.os.Bundle
import android.view.*
import com.martiserramolina.lifeplan.R
import com.martiserramolina.lifeplan.databinding.FragmentNavSituationBinding
import com.martiserramolina.lifeplan.ui.activities.MainActivity
import com.martiserramolina.lifeplan.ui.fragments.FragmentWithBinding
import com.martiserramolina.lifeplan.ui.adapters.SituationDayAdapter
import com.martiserramolina.lifeplan.ui.fragments.MainFragmentDirections

class SituationFragment : FragmentWithBinding<FragmentNavSituationBinding>() {

    private val mainActivity by lazy { activity as MainActivity }

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
        binding.fragmentNavSituationRv.adapter = SituationDayAdapter()
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

    private fun navigateToAddSituationDayFragment() {
        mainActivity.navController
            .navigate(MainFragmentDirections.actionMainFragmentToAddSituationDayFragment())
    }
}