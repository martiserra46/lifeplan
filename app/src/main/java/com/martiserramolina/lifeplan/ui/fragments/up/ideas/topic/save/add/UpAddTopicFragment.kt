package com.martiserramolina.lifeplan.ui.fragments.up.ideas.topic.save.add

import com.martiserramolina.lifeplan.R
import com.martiserramolina.lifeplan.utils.enums.NavSection
import com.martiserramolina.lifeplan.ui.fragments.up.ideas.topic.save.UpSaveTopicFragment
import com.martiserramolina.lifeplan.viewmodels.factory.ViewModelFactory
import com.martiserramolina.lifeplan.viewmodels.viewmodels.sections.ideas.topic.save.add.AddTopicViewModel

class UpAddTopicFragment : UpSaveTopicFragment() {

    override val viewModel by ViewModelFactory.Delegate(
        this, AddTopicViewModel::class.java
    ) { AddTopicViewModel(mainActivity.application) }

    override fun getMenuResource(): Int = R.menu.ideas_topic_add_menu

    override fun getSaveMenuItemId(): Int = R.id.ideas_topic_add_save_mi

    override fun navigateToPreviousFragment() {
        mainActivity.navController.navigate(
            UpAddTopicFragmentDirections.actionAddTopicFragmentToMainFragment(NavSection.IDEAS)
        )
    }

    override fun getTopicSavedMessage(): Int = R.string.notebook_added
}