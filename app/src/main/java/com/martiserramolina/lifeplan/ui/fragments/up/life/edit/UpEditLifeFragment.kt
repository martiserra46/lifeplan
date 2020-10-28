package com.martiserramolina.lifeplan.ui.fragments.up.life.edit

import android.os.Bundle
import android.view.*
import androidx.appcompat.widget.Toolbar
import androidx.lifecycle.ViewModelProvider
import com.martiserramolina.lifeplan.R
import com.martiserramolina.lifeplan.databinding.FragmentNavLifeSaveBinding
import com.martiserramolina.lifeplan.enums.NavSection
import com.martiserramolina.lifeplan.ui.fragments.interfaces.OnSaveMenuItemClickListener
import com.martiserramolina.lifeplan.ui.fragments.up.life.UpLifeFragment
import com.martiserramolina.lifeplan.viewmodels.viewmodels.life.edit.EditLifeViewModel

class UpEditLifeFragment :
    UpLifeFragment<FragmentNavLifeSaveBinding>(),
    OnSaveMenuItemClickListener
{

    private val viewModel by lazy {
        ViewModelProvider(
            this,
            EditLifeViewModel.Factory(
                EditLifeFragmentArgs.fromBundle(requireArguments()).life,
                requireActivity().application
            )
        ).get(EditLifeViewModel::class.java)
    }

    override fun buildBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ): FragmentNavLifeSaveBinding {
        return FragmentNavLifeSaveBinding.inflate(inflater, container, false)
    }

    override fun getToolbar(): Toolbar = binding.fragmentNavLifeSaveTb

    override fun getToolbarTitle(): String = getString(R.string.life_edit)

    override fun navigateToPreviousFragment() {
        mainActivity.navController.navigate(
            EditLifeFragmentDirections.actionEditLifeFragmentToMainFragment(NavSection.LIFE)
        )
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupTextTv()
        navigateToPreviousFragmentAfterDbOp()
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.life_edit_menu, menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.life_edit_save_mi -> onSaveMenuItemClicked()
            else -> super.onOptionsItemSelected(item)
        }
    }

    private fun setupTextTv() {
        binding.fragmentNavLifeSaveDescriptionEt.setText(viewModel.life.lifeText)
    }

    override fun onSaveMenuItemClicked(): Boolean {
        editLife()
        return true
    }

    private fun editLife() {
        viewModel.life.apply {
            lifeText = getText()
        }
        viewModel.editLife()
    }

    private fun getText(): String {
        return binding.fragmentNavLifeSaveDescriptionEt.text.toString()
    }

    private fun navigateToPreviousFragmentAfterDbOp() {
        viewModel.lifeEdited.observe(viewLifecycleOwner) { lifeInserted ->
            if (lifeInserted) navigateToPreviousFragment()
        }
    }
}