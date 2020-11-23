package com.martiserramolina.lifeplan.ui.fragments.sections.up.status.day.info

import android.os.Bundle
import android.view.*
import androidx.appcompat.widget.Toolbar
import androidx.core.content.ContextCompat
import com.martiserramolina.lifeplan.R
import com.martiserramolina.lifeplan.databinding.FragmentNavStatusDayBinding
import com.martiserramolina.lifeplan.ui.dialogs.DeleteItemDialogFragment
import com.martiserramolina.lifeplan.utils.enums.NavSection
import com.martiserramolina.lifeplan.utils.functions.dayString
import com.martiserramolina.lifeplan.utils.functions.showMessage
import com.martiserramolina.lifeplan.ui.fragments.sections.up.status.day.UpDayFragment
import com.martiserramolina.lifeplan.utils.interfaces.InfoItemFragment
import com.martiserramolina.lifeplan.viewmodels.factory.ViewModelFactory
import com.martiserramolina.lifeplan.viewmodels.viewmodels.sections.status.day.info.InfoDayViewModel

class UpInfoDayFragment : UpDayFragment<FragmentNavStatusDayBinding>(), InfoItemFragment {

    private val viewModel by ViewModelFactory.Delegate(
        this, InfoDayViewModel::class.java
    ) {
        val args = UpInfoDayFragmentArgs.fromBundle(requireArguments())
        InfoDayViewModel(args.day, mainActivity.application)
    }

    override fun buildBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ): FragmentNavStatusDayBinding = FragmentNavStatusDayBinding.inflate(
        inflater, container, false
    )

    override fun getToolbar(): Toolbar = binding.fragmentNavStatusDayTb

    override fun navigateToPreviousFragment() {
        mainActivity.navController.navigate(
            UpInfoDayFragmentDirections.actionDayFragmentToMainFragment(NavSection.STATUS)
        )
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupViews {
            setupDateTextView()
            setupSatisfactionTextView()
            setupDescriptionTextView()
        }
        setupWhenItemDeletedFunctionality(viewModel, viewLifecycleOwner) {
            navigateToPreviousFragment()
            showMessage(binding.root, R.string.day_deleted)
        }
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.status_day_menu, menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.status_day_edit_mi -> onEditMenuItemSelected { navigateToEditDayFragment() }
            R.id.status_day_delete_mi -> onDeleteMenuItemSelected { deleteDay() }
            else -> super.onOptionsItemSelected(item)
        }
    }

    private fun setupDateTextView() {
        binding.fragmentNavStatusDayDateTv.text = viewModel.day
            .dayDate.dayString()
    }

    private fun setupSatisfactionTextView() {
        viewModel.day.daySatisfaction.let { satisfaction ->
            binding.fragmentNavStatusDaySatisfactionTv.apply {
                text = getString(satisfaction.stringId)
                setTextColor(ContextCompat.getColor(context, satisfaction.colorId))
            }
        }
    }

    private fun setupDescriptionTextView() {
        binding.fragmentNavStatusDayDescriptionTv.text = viewModel.day.dayText
    }

    private fun navigateToEditDayFragment() {
        mainActivity.navController.navigate(
            UpInfoDayFragmentDirections.actionDayFragmentToEditDayFragment(viewModel.day)
        )
    }

    private fun deleteDay() {
        DeleteItemDialogFragment(
            R.string.dialog_message_delete_notebook,
            { viewModel.deleteItem() }
        ).show(parentFragmentManager, getString(R.string.dialog_message_delete_notebook))
    }
}