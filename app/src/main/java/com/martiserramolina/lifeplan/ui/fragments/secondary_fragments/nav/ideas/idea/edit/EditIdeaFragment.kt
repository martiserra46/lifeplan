package com.martiserramolina.lifeplan.ui.fragments.secondary_fragments.nav.ideas.idea.edit

import android.view.LayoutInflater
import android.view.ViewGroup
import com.martiserramolina.lifeplan.databinding.FragmentNavIdeasIdeaSaveBinding
import com.martiserramolina.lifeplan.ui.fragments.BaseFragment

class EditIdeaFragment : BaseFragment<FragmentNavIdeasIdeaSaveBinding>() {
    override fun getBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ): FragmentNavIdeasIdeaSaveBinding {
        return FragmentNavIdeasIdeaSaveBinding.inflate(inflater, container, false)
    }

}