package com.martiserramolina.lifeplan.ui.fragments.nav.ideas.topic.add

import android.view.*
import androidx.appcompat.widget.Toolbar
import androidx.lifecycle.ViewModelProvider
import com.martiserramolina.lifeplan.R
import com.martiserramolina.lifeplan.databinding.FragmentNavIdeasTopicSaveBinding
import com.martiserramolina.lifeplan.enums.NavSection
import com.martiserramolina.lifeplan.repository.model.Topic
import com.martiserramolina.lifeplan.ui.fragments.SecondaryFragment
import com.martiserramolina.lifeplan.viewmodels.ideas.topic.add.AddTopicViewModel

class AddTopicFragment : SecondaryFragment<FragmentNavIdeasTopicSaveBinding>() {

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

    override fun getRootView(): View = binding.root

    override fun getToolbar(): Toolbar = binding.fragmentNavIdeasTopicSaveTb

    override fun getTitleId(): Int = R.string.ideas_topic_add

    override fun navigateToPreviousFragment() {
        navController.navigate(
            AddTopicFragmentDirections.actionAddTopicFragmentToMainFragment(NavSection.IDEAS)
        )
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
        viewModel.insertTopic(Topic(binding.fragmentNavIdeasTopicSaveTitleEt.text.toString()))
        navigateToPreviousFragment()
    }
}