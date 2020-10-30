package com.martiserramolina.lifeplan.ui.fragments.up.life.edit

import android.os.Bundle
import android.view.*
import androidx.appcompat.widget.Toolbar
import androidx.core.content.ContextCompat
import com.google.android.material.snackbar.Snackbar
import com.martiserramolina.lifeplan.R
import com.martiserramolina.lifeplan.databinding.FragmentNavLifeSaveBinding
import com.martiserramolina.lifeplan.enums.NavSection
import com.martiserramolina.lifeplan.functions.showMessage
import com.martiserramolina.lifeplan.repository.room.Day
import com.martiserramolina.lifeplan.ui.fragments.up.life.UpLifeFragment
import com.martiserramolina.lifeplan.viewmodels.factory.ViewModelFactory
import com.martiserramolina.lifeplan.viewmodels.viewmodels.sections.life.save.SaveLifeViewModel

class UpEditLifeFragment : UpLifeFragment<FragmentNavLifeSaveBinding>() {

    private val viewModel by ViewModelFactory.Delegate(
        this, SaveLifeViewModel::class.java
    ) {
        val args = UpEditLifeFragmentArgs.fromBundle(requireArguments())
        SaveLifeViewModel(args.life, mainActivity.application)
    }

    override fun buildBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ): FragmentNavLifeSaveBinding = FragmentNavLifeSaveBinding.inflate(
        inflater, container, false
    )

    override fun getToolbar(): Toolbar = binding.fragmentNavLifeSaveTb

    override fun getToolbarTitle(): String = getString(R.string.life_edit)

    override fun navigateToPreviousFragment() {
        mainActivity.navController.navigate(
            UpEditLifeFragmentDirections.actionEditLifeFragmentToMainFragment(NavSection.LIFE)
        )
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupViews()
        setupWhenLifeSavedFunctionality()
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.life_edit_menu, menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.life_edit_save_mi -> onSaveMenuItemSelected()
            else -> super.onOptionsItemSelected(item)
        }
    }

    private fun setupViews() {
        setupTextTextView()
    }

    private fun setupWhenLifeSavedFunctionality() {
        viewModel.lifeSaved.observe(viewLifecycleOwner) { lifeSaved ->
            if (lifeSaved) {
                navigateToPreviousFragment()
                showMessage(binding.root, R.string.life_saved)
            }
        }
    }

    private fun onSaveMenuItemSelected(): Boolean = saveLife().run { true }

    private fun setupTextTextView() {
        binding.fragmentNavLifeSaveTextEt.setText(viewModel.life.lifeText)
    }

    private fun saveLife() {
        viewModel.life.apply {
            lifeText = getTextFromTextEditText()
        }
        viewModel.saveLife()
    }

    private fun getTextFromTextEditText(): String {
        return binding.fragmentNavLifeSaveTextEt.text.toString()
    }
}