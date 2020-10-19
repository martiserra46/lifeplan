package com.martiserramolina.lifeplan.ui.fragments.secondary_fragments.nav.your_life.edit

import android.os.Bundle
import android.view.*
import androidx.appcompat.widget.Toolbar
import androidx.lifecycle.ViewModelProvider
import com.martiserramolina.lifeplan.R
import com.martiserramolina.lifeplan.databinding.FragmentNavYourLifeSaveBinding
import com.martiserramolina.lifeplan.enums.NavSection
import com.martiserramolina.lifeplan.repository.model.YourLife
import com.martiserramolina.lifeplan.ui.fragments.secondary_fragments.SecondaryFragment
import com.martiserramolina.lifeplan.viewmodels.your_life.edit.EditYourLifeViewModel

class EditYourLifeFragment : SecondaryFragment<FragmentNavYourLifeSaveBinding>() {

    private val viewModel by lazy {
        ViewModelProvider(
            this, EditYourLifeViewModel.Factory(requireActivity().application)
        ).get(EditYourLifeViewModel::class.java)
    }

    override fun getBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ): FragmentNavYourLifeSaveBinding {
        return FragmentNavYourLifeSaveBinding.inflate(inflater, container, false)
    }

    override fun getToolbar(): Toolbar = binding.fragmentNavYourLifeSaveTb

    override fun getToolbarTitle(): String = getString(R.string.your_life_edit)

    override fun navigateToPreviousFragment() {
        navController.navigate(
            EditYourLifeFragmentDirections
                .actionEditYourLifeFragmentToMainFragment(NavSection.YOUR_LIFE)
        )
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupDescription()
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.your_life_edit_menu, menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.your_life_edit_save_mi -> {
                saveDescription()
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

    private fun setupDescription() {
        viewModel.yourLife.observe(viewLifecycleOwner) { yourLife ->
            yourLife?.let { binding.fragmentNavYourLifeSaveDescriptionEt.setText(yourLife.text) }
        }
    }

    private fun saveDescription() {
        viewModel.insertYourLife(
            YourLife(binding.fragmentNavYourLifeSaveDescriptionEt.text.toString())
        )
        navigateToPreviousFragment()
    }
}