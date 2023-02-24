package ru.maxstelmakh.mymoney.presentation.adapter.statisticadapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import ru.maxstelmakh.mymoney.databinding.StatisticItemLayoutBinding
import ru.maxstelmakh.mymoney.domain.model.StatisticModelDomain

class MainStatisticAdapter : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private var oldStatisticList = emptyList<StatisticModelDomain>()


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val view =
            StatisticItemLayoutBinding
                .inflate(LayoutInflater.from(parent.context), parent, false)
        return MainStatViewHolder(view)
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        (holder as MainStatViewHolder).showList(oldStatisticList[position])
    }

    override fun getItemCount() = oldStatisticList.size

    fun setList(newStatisticList: List<StatisticModelDomain>) {
        val diffUtil = StatisticDiffUtil(oldStatisticList, newStatisticList)
        val diffResults = DiffUtil.calculateDiff(diffUtil)
        oldStatisticList = newStatisticList
        diffResults.dispatchUpdatesTo(this)
    }
}