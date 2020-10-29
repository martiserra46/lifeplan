package com.martiserramolina.lifeplan.ui.adapters.recyclerview.adapters.situation

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.martiserramolina.lifeplan.databinding.RviSituationDayBinding
import com.martiserramolina.lifeplan.extensions.formatted
import com.martiserramolina.lifeplan.repository.room.Day

class DayAdapter(
    private val onItemClick: (Day) -> Unit
) : RecyclerView.Adapter<DayAdapter.ViewHolder>() {

    var listDays = emptyList<Day>()
        set(value) {
            val oldValue = field
            field = value.toList()
            DiffUtil.calculateDiff(DayListDiffCallback(oldValue, field))
                .dispatchUpdatesTo(this)
        }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder.create(parent, onItemClick)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(listDays[position])
    }

    override fun getItemCount(): Int {
        return listDays.size
    }

    class ViewHolder(
        private val binding: RviSituationDayBinding,
        private val onItemClick: (Day) -> Unit
    ) : RecyclerView.ViewHolder(binding.root) {
        companion object {
            fun create(parent: ViewGroup, onItemClick: (Day) -> Unit): ViewHolder {
                return ViewHolder(
                    RviSituationDayBinding.inflate(
                        LayoutInflater.from(parent.context), parent, false
                    ), onItemClick
                )
            }
        }

        fun bind(day: Day) {
            binding.apply {
                rviSituationDayDateTv.text = day.dayDate.formatted()
                rviSituationDayTextTv.text = day.dayText
                rviSituationDayCircleV.setBackgroundResource(day.daySatisfaction.drawableId)
                root.setOnClickListener { onItemClick(day) }
            }
        }
    }

    class DayListDiffCallback(
        private val oldDayList: List<Day>, private val newDayList: List<Day>
    ) : DiffUtil.Callback() {
        override fun getOldListSize() = oldDayList.size
        override fun getNewListSize() = newDayList.size
        override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int) =
            oldDayList[oldItemPosition].dayId == newDayList[newItemPosition].dayId
        override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int) =
            oldDayList[oldItemPosition] == newDayList[newItemPosition]
    }
}