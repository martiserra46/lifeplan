package com.martiserramolina.lifeplan.adapters.spinner.day_satisfaction

import android.content.Context
import com.martiserramolina.lifeplan.adapters.spinner.SpinnerItemAdapter
import com.martiserramolina.lifeplan.repository.enums.DaySatisfaction

class DaySatisfactionAdapter(
    context: Context
) : SpinnerItemAdapter<DaySatisfaction>(context, DaySatisfaction.values()) {
    override fun getStringId(value: DaySatisfaction): Int = value.stringId
    override fun getColorId(value: DaySatisfaction): Int = value.colorId
}