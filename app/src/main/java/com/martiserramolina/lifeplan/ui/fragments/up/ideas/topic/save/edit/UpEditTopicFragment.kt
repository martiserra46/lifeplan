package com.martiserramolina.lifeplan.ui.fragments.up.ideas.topic.save.edit

import com.martiserramolina.lifeplan.R
import com.martiserramolina.lifeplan.ui.fragments.up.ideas.topic.save.UpSaveTopicFragment
import com.martiserramolina.lifeplan.viewmodels.factory.ViewModelFactory
import com.martiserramolina.lifeplan.viewmodels.viewmodels.sections.ideas.topic.save.edit.EditTopicViewModel

class UpEditTopicFragment : UpSaveTopicFragment() {

    override val viewModel by ViewModelFactory.Delegate(
        this, EditTopicViewModel::class.java
    ) {
        val args = UpEditTopicFragmentArgs.fromBundle(requireArguments())
        EditTopicViewModel(args.topic, mainActivity.application)
    }

    override fun getMenuResource(): Int = R.menu.ideas_topic_edit_menu

    override fun getSaveMenuItemId(): Int = R.id.ideas_topic_edit_save_mi

    override fun navigateToPreviousFragment() {
        mainActivity.navController.navigate(
            UpEditTopicFragmentDirections.actionEditTopicFragmentToTopicFragment(viewModel.topic)
        )
    }

    override fun getTopicSavedMessage(): Int = R.string.notebook_edited
}