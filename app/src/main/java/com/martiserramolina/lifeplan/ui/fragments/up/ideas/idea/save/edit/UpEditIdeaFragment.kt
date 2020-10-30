package com.martiserramolina.lifeplan.ui.fragments.up.ideas.idea.save.edit

import com.martiserramolina.lifeplan.R
import com.martiserramolina.lifeplan.ui.fragments.up.ideas.idea.save.UpSaveIdeaFragment
import com.martiserramolina.lifeplan.viewmodels.factory.ViewModelFactory
import com.martiserramolina.lifeplan.viewmodels.viewmodels.sections.ideas.idea.save.edit.EditIdeaViewModel

class UpEditIdeaFragment : UpSaveIdeaFragment() {

    override val viewModel by ViewModelFactory.Delegate(
        this, EditIdeaViewModel::class.java
    ) {
        val args = UpEditIdeaFragmentArgs.fromBundle(requireArguments())
        EditIdeaViewModel(args.idea, args.topic, mainActivity.application)
    }

    override fun getMenuResource(): Int = R.menu.ideas_idea_edit_menu

    override fun getSaveMenuItemId(): Int = R.id.ideas_idea_edit_save_mi

    override fun navigateToPreviousFragment() {
        mainActivity.navController.navigate(
            UpEditIdeaFragmentDirections
                .actionEditIdeaFragmentToIdeaFragment(viewModel.idea, viewModel.topic)
        )
    }

    override fun getIdeaSavedMessage(): Int = R.string.idea_edited
}