package com.martiserramolina.lifeplan.ui.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.martiserramolina.lifeplan.databinding.RviSituationDayBinding
import com.martiserramolina.lifeplan.extensions.format
import com.martiserramolina.lifeplan.repository.model.SituationDay

class SituationDayAdapter(
    private val onItemClickListener: OnItemClickListener
) : RecyclerView.Adapter<SituationDayAdapter.ViewHolder>() {

    var listSituationDays = emptyList<SituationDay>()
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder.create(parent, onItemClickListener)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(listSituationDays[position])
    }

    override fun getItemCount(): Int {
        return listSituationDays.size
    }

    class ViewHolder(
        private val binding: RviSituationDayBinding,
        private val onItemClickListener: OnItemClickListener
    ) : RecyclerView.ViewHolder(binding.root) {
        companion object {
            fun create(parent: ViewGroup, onItemClickListener: OnItemClickListener): ViewHolder {
                return ViewHolder(
                    RviSituationDayBinding.inflate(
                        LayoutInflater.from(parent.context), parent, false
                    ), onItemClickListener
                )
            }
        }

        fun bind(situationDay: SituationDay) {
            binding.apply {
                rviSituationDayDateTv.text = situationDay.date.format("dd/mm/yyyy")
                rviSituationDayTextTv.text = situationDay.text
                rviSituationDayCircleV
                    .setBackgroundResource(situationDay.satisfaction.drawableId)
                root.setOnClickListener { onItemClickListener.onClick(situationDay) }
            }
        }
    }

    class OnItemClickListener(private val func: (SituationDay) -> Unit) {
        fun onClick(situationDay: SituationDay) { func(situationDay) }
    }
}