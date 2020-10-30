package com.martiserramolina.lifeplan.ui.fragments.up.ideas.idea.save.add

import com.martiserramolina.lifeplan.R
import com.martiserramolina.lifeplan.ui.fragments.up.ideas.idea.save.UpSaveIdeaFragment
import com.martiserramolina.lifeplan.viewmodels.factory.ViewModelFactory
import com.martiserramolina.lifeplan.viewmodels.viewmodels.sections.ideas.idea.save.add.AddIdeaViewModel

class UpAddIdeaFragment : UpSaveIdeaFragment() {

    override val viewModel by ViewModelFactory.Delegate(
        this, AddIdeaViewModel::class.java
    ) {
        val args = UpAddIdeaFragmentArgs.fromBundle(requireArguments())
        AddIdeaViewModel(args.topic, mainActivity.application)
    }

    override fun getMenuResource(): Int = R.menu.ideas_idea_add_menu

    override fun getSaveMenuItemId(): Int = R.id.ideas_idea_add_save_mi

    override fun navigateToPreviousFragment() {
        mainActivity.navController.navigate(
            UpAddIdeaFragmentDirections.actionAddIdeaFragmentToTopicFragment(viewModel.topic)
        )
    }

    override fun getIdeaSavedMessage(): Int = R.string.idea_added
}