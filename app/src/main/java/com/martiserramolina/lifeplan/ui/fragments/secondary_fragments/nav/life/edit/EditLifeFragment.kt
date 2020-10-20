package com.martiserramolina.lifeplan.ui.fragments.secondary_fragments.nav.life.edit

import android.os.Bundle
import android.view.*
import androidx.appcompat.widget.Toolbar
import androidx.lifecycle.ViewModelProvider
import com.martiserramolina.lifeplan.R
import com.martiserramolina.lifeplan.databinding.FragmentNavLifeSaveBinding
import com.martiserramolina.lifeplan.enums.NavSection
import com.martiserramolina.lifeplan.repository.room.Life
import com.martiserramolina.lifeplan.ui.fragments.secondary_fragments.SecondaryFragment
import com.martiserramolina.lifeplan.viewmodels.life.edit.EditLifeViewModel

class EditLifeFragment : SecondaryFragment<FragmentNavLifeSaveBinding>() {

    private val viewModel by lazy {
        ViewModelProvider(
            this,
            EditLifeViewModel.Factory(
                EditLifeFragmentArgs.fromBundle(requireArguments()).life,
                requireActivity().application
            )
        ).get(EditLifeViewModel::class.java)
    }

    override fun getBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ): FragmentNavLifeSaveBinding {
        return FragmentNavLifeSaveBinding.inflate(inflater, container, false)
    }

    override fun getToolbar(): Toolbar = binding.fragmentNavLifeSaveTb

    override fun getToolbarTitle(): String = getString(R.string.life_edit)

    override fun navigateToPreviousFragment() {
        navController.navigate(
            EditLifeFragmentDirections
                .actionEditLifeFragmentToMainFragment(NavSection.LIFE)
        )
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupTextTv()
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.life_edit_menu, menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.life_edit_save_mi -> {
                saveText()
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

    private fun setupTextTv() {
        viewModel.life?.let { binding.fragmentNavLifeSaveDescriptionEt.setText(it.lifeText) }
    }

    private fun saveText() {
        viewModel
            .insertLife(Life(0, binding.fragmentNavLifeSaveDescriptionEt.text.toString()))
        navigateToPreviousFragment()
    }
}