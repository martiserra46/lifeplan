package com.martiserramolina.lifeplan.ui.adapters.recyclerview.viewholders.situation

import com.martiserramolina.lifeplan.databinding.RviSituationDayBinding
import com.martiserramolina.lifeplan.extensions.formatted
import com.martiserramolina.lifeplan.repository.room.Day
import com.martiserramolina.lifeplan.ui.adapters.recyclerview.viewholders.ItemViewHolder

class DayViewHolder(
    binding: RviSituationDayBinding,
    onItemClick: (Day) -> Unit
) : ItemViewHolder<RviSituationDayBinding, Day>(binding, onItemClick) {
    override fun bindData(item: Day) {
        binding.apply {
            rviSituationDayDateTv.text = item.dayDate.formatted()
            rviSituationDayTextTv.text = item.dayText
            rviSituationDayCircleV.setBackgroundResource(item.daySatisfaction.drawableId)
        }
    }
}