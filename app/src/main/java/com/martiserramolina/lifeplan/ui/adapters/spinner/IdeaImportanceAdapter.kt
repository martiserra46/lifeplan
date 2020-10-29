package com.martiserramolina.lifeplan.ui.adapters.spinner

import android.content.Context
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.TextView
import androidx.core.content.ContextCompat
import com.martiserramolina.lifeplan.repository.enums.IdeaImportance

class IdeaImportanceAdapter(
    context: Context, textViewId: Int
) : ArrayAdapter<IdeaImportance>(context, textViewId, IdeaImportance.values()) {

    private val listImportanceLevels = IdeaImportance.values()

    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        val textView = super.getView(position, convertView, parent) as TextView
        return textView.apply {
            text = context.getString(listImportanceLevels[position].stringId)
            setTextColor(ContextCompat.getColor(context, listImportanceLevels[position].colorId))
        }
    }

    override fun getDropDownView(position: Int, convertView: View?, parent: ViewGroup): View {
        val textView = super.getDropDownView(position, convertView, parent) as TextView
        return textView.apply { text = context.getString(listImportanceLevels[position].stringId) }
    }
}