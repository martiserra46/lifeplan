package com.martiserramolina.lifeplan.adapters.recyclerview.viewholders.situation.day

import android.view.LayoutInflater
import android.view.ViewGroup
import com.martiserramolina.lifeplan.R
import com.martiserramolina.lifeplan.databinding.RviSituationDayBinding
import com.martiserramolina.lifeplan.extensions.formatted
import com.martiserramolina.lifeplan.repository.room.Day
import com.martiserramolina.lifeplan.adapters.recyclerview.viewholders.ItemViewHolder
import com.martiserramolina.lifeplan.extensions.formattedWithMaxLength

class DayViewHolder(
    parent: ViewGroup,
    onItemClick: (Day) -> Unit
) : ItemViewHolder<RviSituationDayBinding, Day>(
    RviSituationDayBinding.inflate(
        LayoutInflater.from(parent.context), parent, false
    ), onItemClick
) {
    override fun bindData(item: Day) {
        binding.apply {
            rviSituationDayDateTv.text = item.dayDate.formatted()
            rviSituationDayTextTv.text = item.dayText.formattedWithMaxLength(
                itemView.context.resources
                    .getInteger(R.integer.maxLengthDayDescriptionRecyclerViewItem)
            )
            rviSituationDaySatisfactionV.setBackgroundResource(item.daySatisfaction.drawableId)
        }
    }
}