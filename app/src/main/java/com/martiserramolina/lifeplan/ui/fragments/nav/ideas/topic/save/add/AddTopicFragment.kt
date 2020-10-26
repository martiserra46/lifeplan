package com.martiserramolina.lifeplan.ui.fragments.nav.ideas.topic.save.add

import android.os.Bundle
import android.view.*
import androidx.appcompat.widget.Toolbar
import androidx.lifecycle.ViewModelProvider
import com.martiserramolina.lifeplan.R
import com.martiserramolina.lifeplan.databinding.FragmentNavIdeasTopicSaveBinding
import com.martiserramolina.lifeplan.enums.NavSection
import com.martiserramolina.lifeplan.repository.room.Topic
import com.martiserramolina.lifeplan.ui.fragments.abstracts.UpButtonFragment
import com.martiserramolina.lifeplan.viewmodels.ideas.topic.add.AddTopicViewModel

class AddTopicFragment : UpButtonFragment<FragmentNavIdeasTopicSaveBinding>() {

    private val viewModel by lazy {
        ViewModelProvider(
            this, AddTopicViewModel.Factory(requireActivity().application)
        ).get(AddTopicViewModel::class.java)
    }

    override fun getBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ): FragmentNavIdeasTopicSaveBinding {
        return FragmentNavIdeasTopicSaveBinding.inflate(inflater, container, false)
    }

    override fun getToolbar(): Toolbar = binding.fragmentNavIdeasTopicSaveTb

    override fun getToolbarTitle(): String = getString(R.string.ideas_topic_add)

    override fun navigateToPreviousFragment() {
        navController.navigate(
            AddTopicFragmentDirections.actionAddTopicFragmentToMainFragment(NavSection.IDEAS)
        )
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        navigateToPreviousFragmentAfterDbOp()
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.ideas_topic_add_menu, menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.ideas_topic_add_save_mi -> {
                saveTopic()
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

    private fun saveTopic() {
        viewModel
            .addTopic(Topic(0, binding.fragmentNavIdeasTopicSaveTitleEt.text.toString()))
    }

    private fun navigateToPreviousFragmentAfterDbOp() {
        viewModel.topicAdded.observe(viewLifecycleOwner) { topicAdded ->
            if (topicAdded) navigateToPreviousFragment()
        }
    }
}