package ru.maxstelmakh.mymoney.presentation.adapter.statisticadapter

import android.annotation.SuppressLint
import android.graphics.Color
import android.graphics.PorterDuff
import androidx.recyclerview.widget.RecyclerView
import ru.maxstelmakh.mymoney.databinding.StatisticItemLayoutBinding
import ru.maxstelmakh.mymoney.domain.model.StatisticModelDomain

class MainStatViewHolder(
    private val binding: StatisticItemLayoutBinding
) : RecyclerView.ViewHolder(binding.root) {
    @SuppressLint("SetTextI18n")
    @Suppress("DEPRECATION")
    fun showList(statisticModelDomain: StatisticModelDomain) {


        with(binding) {

            imageCategory.setImageResource(statisticModelDomain.image)
            root
                .background
                .mutate()
                .setColorFilter(
                    Color.parseColor(statisticModelDomain.color),
                    PorterDuff.Mode.SRC_ATOP
                )
            categoryName.text = statisticModelDomain.categoryName

            val rounded = Math.round(statisticModelDomain.percent * 100)
            percentStat.text = "$rounded%"
            spentStat.text = statisticModelDomain.total.toString()
        }
    }

}
