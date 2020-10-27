package com.martiserramolina.lifeplan.ui.fragments.up.ideas.topic.save.edit

import android.os.Bundle
import android.view.*
import androidx.appcompat.widget.Toolbar
import androidx.lifecycle.ViewModelProvider
import com.martiserramolina.lifeplan.R
import com.martiserramolina.lifeplan.databinding.FragmentNavIdeasTopicSaveBinding
import com.martiserramolina.lifeplan.ui.fragments.up.UpButtonFragment
import com.martiserramolina.lifeplan.viewmodels.nav.ideas.topic.save.edit.EditTopicViewModel

class EditTopicFragment : UpButtonFragment<FragmentNavIdeasTopicSaveBinding>() {

    private val viewModel by lazy {
        ViewModelProvider(
            this,
            EditTopicViewModel.Factory(
                EditTopicFragmentArgs.fromBundle(requireArguments()).topic,
                requireActivity().application
            )
        ).get(EditTopicViewModel::class.java)
    }
    
    override fun buildBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ): FragmentNavIdeasTopicSaveBinding {
        return FragmentNavIdeasTopicSaveBinding.inflate(inflater, container, false)
    }

    override fun getToolbar(): Toolbar = binding.fragmentNavIdeasTopicSaveTb

    override fun getToolbarTitle(): String = getString(R.string.edit)

    override fun navigateToPreviousFragment() {
        navController
            .navigate(EditTopicFragmentDirections.actionEditTopicFragmentToTopicFragment(viewModel.topic))
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupTextTv()
        navigateToPreviousFragmentAfterDbOp()
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.ideas_topic_edit_menu, menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.ideas_topic_edit_save_mi -> {
                saveTopic()
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

    private fun saveTopic() {
        viewModel.topic.apply {
            topicText = getText()
        }
        viewModel.editTopic()
    }

    private fun setupTextTv() {
        binding.fragmentNavIdeasTopicSaveTitleEt.setText(viewModel.topic.topicText)
    }

    private fun getText(): String {
        return binding.fragmentNavIdeasTopicSaveTitleEt.text.toString()
    }

    private fun navigateToPreviousFragmentAfterDbOp() {
        viewModel.topicEdited.observe(viewLifecycleOwner) { topicUpdated ->
            if (topicUpdated) navigateToPreviousFragment()
        }
    }
}