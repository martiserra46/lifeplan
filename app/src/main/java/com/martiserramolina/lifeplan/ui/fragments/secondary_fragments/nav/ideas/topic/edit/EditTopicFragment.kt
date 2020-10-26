package com.martiserramolina.lifeplan.ui.fragments.secondary_fragments.nav.ideas.topic.edit

import android.os.Bundle
import android.view.*
import androidx.appcompat.widget.Toolbar
import androidx.lifecycle.ViewModelProvider
import com.martiserramolina.lifeplan.R
import com.martiserramolina.lifeplan.databinding.FragmentNavIdeasTopicSaveBinding
import com.martiserramolina.lifeplan.repository.room.Topic
import com.martiserramolina.lifeplan.ui.fragments.BaseFragment
import com.martiserramolina.lifeplan.ui.fragments.secondary_fragments.SecondaryFragment
import com.martiserramolina.lifeplan.viewmodels.ideas.topic.edit.EditTopicViewModel

class EditTopicFragment : SecondaryFragment<FragmentNavIdeasTopicSaveBinding>() {

    private val viewModel by lazy {
        ViewModelProvider(
            this,
            EditTopicViewModel.Factory(
                EditTopicFragmentArgs.fromBundle(requireArguments()).topic,
                requireActivity().application
            )
        ).get(EditTopicViewModel::class.java)
    }
    
    override fun getBinding(
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
        viewModel.topic = Topic(viewModel.topic.topicId, getText())
        viewModel.updateTopic(viewModel.topic)
    }

    private fun setupTextTv() {
        binding.fragmentNavIdeasTopicSaveTitleEt.setText(viewModel.topic.topicText)
    }

    private fun getText(): String {
        return binding.fragmentNavIdeasTopicSaveTitleEt.text.toString()
    }

    private fun navigateToPreviousFragmentAfterDbOp() {
        viewModel.topicUpdated.observe(viewLifecycleOwner) { topicUpdated ->
            if (topicUpdated) navigateToPreviousFragment()
        }
    }
}