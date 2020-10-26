package com.martiserramolina.lifeplan.ui.fragments.secondary_fragments.nav.ideas.idea

import android.os.Bundle
import android.view.*
import androidx.appcompat.widget.Toolbar
import androidx.core.content.ContextCompat
import androidx.lifecycle.ViewModelProvider
import com.martiserramolina.lifeplan.R
import com.martiserramolina.lifeplan.databinding.FragmentNavIdeasIdeaBinding
import com.martiserramolina.lifeplan.ui.fragments.secondary_fragments.SecondaryFragment
import com.martiserramolina.lifeplan.viewmodels.ideas.idea.IdeaViewModel

class IdeaFragment : SecondaryFragment<FragmentNavIdeasIdeaBinding>() {

    private val viewModel by lazy {
        val args = IdeaFragmentArgs.fromBundle(requireArguments())
        ViewModelProvider(
            this,
            IdeaViewModel.Factory(args.topic, args.idea, requireActivity().application)
        ).get(IdeaViewModel::class.java)
    }

    override fun getBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ): FragmentNavIdeasIdeaBinding {
        return FragmentNavIdeasIdeaBinding.inflate(inflater, container, false)
    }

    override fun getToolbar(): Toolbar = binding.fragmentNavIdeasIdeaTb

    override fun getToolbarTitle(): String = ""

    override fun navigateToPreviousFragment() {
        navController
            .navigate(IdeaFragmentDirections.actionIdeaFragmentToTopicFragment(viewModel.topic))
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupTitleTv()
        setupImportanceTv()
        setupDescriptionTv()
        navigateToPreviousFragmentAfterDbOp()
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.ideas_idea_menu, menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.ideas_idea_edit_mi -> {
                navigateToEditIdeaFragment()
                true
            }
            R.id.ideas_idea_delete_mi -> {
                deleteIdea()
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

    private fun setupTitleTv() {
        binding.fragmentNavIdeasIdeaTitleTv.text = viewModel.idea.ideaTitle
    }

    private fun setupImportanceTv() {
        viewModel.idea.ideaImportance.let { importance ->
            binding.fragmentNavIdeasIdeaImportanceTv.apply {
                text = getString(importance.stringId)
                setTextColor(ContextCompat.getColor(context, importance.colorId))
            }
        }
    }

    private fun setupDescriptionTv() {
        binding.fragmentNavIdeasIdeaDescriptionTv.text = viewModel.idea.ideaDescription
    }

    private fun navigateToEditIdeaFragment() {
        navController.navigate(
            IdeaFragmentDirections
                .actionIdeaFragmentToEditIdeaFragment(viewModel.idea, viewModel.topic)
        )
    }

    private fun deleteIdea() {
        viewModel.deleteIdea()
    }

    private fun navigateToPreviousFragmentAfterDbOp() {
        viewModel.ideaDeleted.observe(viewLifecycleOwner) { ideaDeleted ->
            if (ideaDeleted) navigateToPreviousFragment()
        }
    }
}