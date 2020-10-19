package com.martiserramolina.lifeplan.ui.adapters

import android.content.Context
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.TextView
import androidx.core.content.ContextCompat
import com.martiserramolina.lifeplan.repository.enums.SituationDaySatisfaction

class SituationDaySatisfactionAdapter(
    context: Context, textViewId: Int
) : ArrayAdapter<SituationDaySatisfaction>(context, textViewId, SituationDaySatisfaction.values()) {

    private val listSatisfactions = SituationDaySatisfaction.values()

    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        val textView = super.getView(position, convertView, parent) as TextView
        return textView.apply {
            text = context.getString(listSatisfactions[position].stringId)
            setTextColor(ContextCompat.getColor(context, listSatisfactions[position].colorId))
        }
    }

    override fun getDropDownView(position: Int, convertView: View?, parent: ViewGroup): View {
        val textView = super.getDropDownView(position, convertView, parent) as TextView
        return textView.apply { text = context.getString(listSatisfactions[position].stringId) }
    }
}