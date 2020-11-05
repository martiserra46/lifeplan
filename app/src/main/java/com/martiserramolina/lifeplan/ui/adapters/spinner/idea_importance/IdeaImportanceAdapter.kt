package com.martiserramolina.lifeplan.ui.adapters.spinner.idea_importance

import android.content.Context
import com.martiserramolina.lifeplan.ui.adapters.spinner.SpinnerItemAdapter
import com.martiserramolina.lifeplan.repository.enums.IdeaImportance

class IdeaImportanceAdapter(
    context: Context
) : SpinnerItemAdapter<IdeaImportance>(context, IdeaImportance.values()) {
    override fun getStringId(value: IdeaImportance): Int = value.stringId
    override fun getColorId(value: IdeaImportance): Int = value.colorId
}