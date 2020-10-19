package com.martiserramolina.lifeplan.ui.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.martiserramolina.lifeplan.databinding.RviSituationDayBinding
import com.martiserramolina.lifeplan.extensions.format
import com.martiserramolina.lifeplan.repository.model.SituationDay

class SituationDayAdapter(
    private val onItemClick: (SituationDay) -> Unit
) : RecyclerView.Adapter<SituationDayAdapter.ViewHolder>() {

    var listSituationDays = emptyList<SituationDay>()
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder.create(parent, onItemClick)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(listSituationDays[position])
    }

    override fun getItemCount(): Int {
        return listSituationDays.size
    }

    class ViewHolder(
        private val binding: RviSituationDayBinding,
        private val onItemClick: (SituationDay) -> Unit
    ) : RecyclerView.ViewHolder(binding.root) {
        companion object {
            fun create(parent: ViewGroup, onItemClick: (SituationDay) -> Unit): ViewHolder {
                return ViewHolder(
                    RviSituationDayBinding.inflate(
                        LayoutInflater.from(parent.context), parent, false
                    ), onItemClick
                )
            }
        }

        fun bind(situationDay: SituationDay) {
            binding.apply {
                rviSituationDayDateTv.text = situationDay.date.format("dd/mm/yyyy")
                rviSituationDayTextTv.text = situationDay.text
                rviSituationDayCircleV
                    .setBackgroundResource(situationDay.satisfaction.drawableId)
                root.setOnClickListener { onItemClick(situationDay) }
            }
        }
    }
}