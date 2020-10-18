package com.martiserramolina.lifeplan.ui.fragments.secondary_fragments.nav.ideas.idea

import android.view.LayoutInflater
import android.view.ViewGroup
import com.martiserramolina.lifeplan.databinding.FragmentNavIdeasIdeaBinding
import com.martiserramolina.lifeplan.ui.fragments.BaseFragment

class IdeaFragment : BaseFragment<FragmentNavIdeasIdeaBinding>() {
    override fun getBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ): FragmentNavIdeasIdeaBinding {
        return FragmentNavIdeasIdeaBinding.inflate(inflater, container, false)
    }

}