package com.sntsb.dexgo.pokemon.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.sntsb.dexgo.databinding.ItemStatisticBinding
import com.sntsb.dexgo.pokemon.dto.StatisticDTO
import com.sntsb.dexgo.utils.UiUtils

class StatisticAdapter(private val statisticDTOList: List<StatisticDTO>, val context: Context) :
    RecyclerView.Adapter<StatisticAdapter.StatisticItemViewHolder>() {

    inner class StatisticItemViewHolder(private val databinding: ItemStatisticBinding) :
        RecyclerView.ViewHolder(databinding.root) {

        fun bind(type: StatisticDTO) {
            databinding.statusItemDataBinding = type
            databinding.tvStatistic.text = UiUtils(context).getStatusLabel(type.description)
            databinding.tvValue.text = buildString { append(type.value) }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): StatisticItemViewHolder {
        val binding = ItemStatisticBinding.inflate(
            LayoutInflater.from(parent.context), parent, false
        )
        return StatisticItemViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return statisticDTOList.size
    }

    override fun onBindViewHolder(holder: StatisticItemViewHolder, position: Int) {
        val statusDTO = statisticDTOList[position]
        holder.bind(statusDTO)
    }


}