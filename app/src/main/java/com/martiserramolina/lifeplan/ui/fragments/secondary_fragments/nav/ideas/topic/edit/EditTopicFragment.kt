package com.martiserramolina.lifeplan.ui.fragments.secondary_fragments.nav.ideas.topic.edit

import android.view.LayoutInflater
import android.view.ViewGroup
import com.martiserramolina.lifeplan.databinding.FragmentNavIdeasTopicSaveBinding
import com.martiserramolina.lifeplan.ui.fragments.BaseFragment

class EditTopicFragment : BaseFragment<FragmentNavIdeasTopicSaveBinding>() {
    override fun getBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ): FragmentNavIdeasTopicSaveBinding {
        return FragmentNavIdeasTopicSaveBinding.inflate(inflater, container, false)
    }
}