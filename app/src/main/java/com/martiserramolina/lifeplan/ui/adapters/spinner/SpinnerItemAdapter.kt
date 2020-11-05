package com.martiserramolina.lifeplan.ui.adapters.spinner

import android.content.Context
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.TextView
import androidx.core.content.ContextCompat
import com.martiserramolina.lifeplan.R

abstract class SpinnerItemAdapter<T>(
    context: Context,
    private val values: Array<T>
) : ArrayAdapter<T>(context, R.layout.spinner_item, values) {

    init { setDropDownViewResource(R.layout.spinner_dropdown_item) }

    final override fun setDropDownViewResource(resource: Int) {
        super.setDropDownViewResource(resource)
    }

    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        val textView = super.getView(position, convertView, parent) as TextView
        return textView.apply {
            text = context.getString(getStringId(values[position]))
            setTextColor(ContextCompat.getColor(context, getColorId(values[position])))
        }
    }

    override fun getDropDownView(position: Int, convertView: View?, parent: ViewGroup): View {
        val textView = super.getDropDownView(position, convertView, parent) as TextView
        return textView.apply { text = context.getString(getStringId(values[position])) }
    }

    abstract fun getStringId(value: T): Int

    abstract fun getColorId(value: T): Int
}