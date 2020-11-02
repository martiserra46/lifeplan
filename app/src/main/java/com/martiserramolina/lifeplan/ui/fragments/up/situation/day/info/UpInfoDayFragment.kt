package com.martiserramolina.lifeplan.ui.fragments.up.situation.day.info

import android.os.Bundle
import android.view.*
import androidx.appcompat.widget.Toolbar
import androidx.core.content.ContextCompat
import com.martiserramolina.lifeplan.R
import com.martiserramolina.lifeplan.databinding.FragmentNavSituationDayBinding
import com.martiserramolina.lifeplan.dialogs.DeleteItemDialogFragment
import com.martiserramolina.lifeplan.enums.NavSection
import com.martiserramolina.lifeplan.extensions.formatted
import com.martiserramolina.lifeplan.functions.showMessageWithDelay
import com.martiserramolina.lifeplan.ui.fragments.up.situation.day.UpDayFragment
import com.martiserramolina.lifeplan.viewmodels.factory.ViewModelFactory
import com.martiserramolina.lifeplan.viewmodels.viewmodels.sections.situation.day.info.InfoDayViewModel

class UpInfoDayFragment : UpDayFragment<FragmentNavSituationDayBinding>() {

    private val viewModel by ViewModelFactory.Delegate(
        this, InfoDayViewModel::class.java
    ) {
        val args = UpInfoDayFragmentArgs.fromBundle(requireArguments())
        InfoDayViewModel(args.day, mainActivity.application)
    }

    override fun buildBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ): FragmentNavSituationDayBinding = FragmentNavSituationDayBinding.inflate(
        inflater, container, false
    )

    override fun getToolbar(): Toolbar = binding.fragmentNavSituationDayTb

    override fun navigateToPreviousFragment() {
        mainActivity.navController.navigate(
            UpInfoDayFragmentDirections.actionDayFragmentToMainFragment(NavSection.SITUATION)
        )
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupViews()
        setupWhenDayDeletedFunctionality()
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.situation_day_menu, menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.situation_day_edit_mi -> onEditMenuItemSelected()
            R.id.situation_day_delete_mi -> onDeleteMenuItemSelected()
            else -> super.onOptionsItemSelected(item)
        }
    }

    private fun setupViews() {
        setupDateTextView()
        setupSatisfactionTextView()
        setupDescriptionTextView()
    }

    private fun setupWhenDayDeletedFunctionality() {
        viewModel.dayDeleted.observe(viewLifecycleOwner) { dayDeleted ->
            if (dayDeleted) {
                navigateToPreviousFragment()
                showMessageWithDelay(binding.root, R.string.day_deleted)
            }
        }
    }

    private fun onEditMenuItemSelected(): Boolean = navigateToEditDayFragment().run { true }

    private fun onDeleteMenuItemSelected(): Boolean = deleteDay().run { true }

    private fun setupDateTextView() {
        binding.fragmentNavSituationDayDateTv.text = viewModel.day
            .dayDate.formatted()
    }

    private fun setupSatisfactionTextView() {
        viewModel.day.daySatisfaction.let { satisfaction ->
            binding.fragmentNavSituationDaySatisfactionTv.apply {
                text = getString(satisfaction.stringId)
                setTextColor(ContextCompat.getColor(context, satisfaction.colorId))
            }
        }
    }

    private fun setupDescriptionTextView() {
        binding.fragmentNavSituationDayDescriptionTv.text = viewModel.day.dayText
    }

    private fun navigateToEditDayFragment() {
        mainActivity.navController.navigate(
            UpInfoDayFragmentDirections.actionDayFragmentToEditDayFragment(viewModel.day)
        )
    }

    private fun deleteDay() {
        DeleteItemDialogFragment(
            R.string.dialog_message_delete_notebook,
            { viewModel.deleteDay() }
        ).show(parentFragmentManager, getString(R.string.dialog_message_delete_notebook))
    }
}