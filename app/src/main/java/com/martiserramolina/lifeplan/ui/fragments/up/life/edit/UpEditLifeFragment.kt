package com.martiserramolina.lifeplan.ui.fragments.up.life.edit

import android.os.Bundle
import android.view.*
import androidx.appcompat.widget.Toolbar
import com.martiserramolina.lifeplan.R
import com.martiserramolina.lifeplan.databinding.FragmentNavLifeSaveBinding
import com.martiserramolina.lifeplan.enums.NavSection
import com.martiserramolina.lifeplan.ui.fragments.up.life.UpLifeFragment
import com.martiserramolina.lifeplan.viewmodels.factory.ViewModelFactory
import com.martiserramolina.lifeplan.viewmodels.viewmodels.life.save.SaveLifeViewModel

class UpEditLifeFragment : UpLifeFragment<FragmentNavLifeSaveBinding>() {

    private val viewModel by ViewModelFactory.Delegate(
        this, SaveLifeViewModel::class.java
    ) {
        val args = EditLifeFragmentArgs.fromBundle(requireArguments())
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
            EditLifeFragmentDirections.actionEditLifeFragmentToMainFragment(NavSection.LIFE)
        )
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupTextTextView()
        whenLifeSavedNavigateToPreviousFragment()
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

    private fun setupTextTextView() {
        setTextToTextEditText(viewModel.life.lifeText)
    }

    private fun whenLifeSavedNavigateToPreviousFragment() {
        viewModel.lifeSaved.observe(viewLifecycleOwner) {
            if (it) navigateToPreviousFragment()
        }
    }

    private fun onSaveMenuItemSelected(): Boolean = saveLife().run { true }

    private fun saveLife() {
        viewModel.life.apply {
            lifeText = getTextFromTextEditText()
        }
        viewModel.saveLife()
    }

    private fun setTextToTextEditText(text: String) {
        binding.fragmentNavLifeSaveTextEt.setText(text)
    }

    private fun getTextFromTextEditText(): String {
        return binding.fragmentNavLifeSaveTextEt.text.toString()
    }
}