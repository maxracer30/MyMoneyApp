package ru.maxstelmakh.mymoney.presentation.adapter.statisticadapter

import androidx.recyclerview.widget.DiffUtil
import ru.maxstelmakh.mymoney.domain.model.StatisticModelDomain

class StatisticDiffUtil(
    private val oldList: List<StatisticModelDomain>,
    private val newList: List<StatisticModelDomain>
) : DiffUtil.Callback() {
    override fun getOldListSize() = oldList.size

    override fun getNewListSize() = newList.size

    override fun areItemsTheSame(
        oldItemPosition: Int, newItemPosition: Int
    ) = oldList[oldItemPosition].categoryId == newList[newItemPosition].categoryId

    override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        return when {
            oldList[oldItemPosition].categoryId != newList[newItemPosition].categoryId -> false
            oldList[oldItemPosition].categoryName != newList[newItemPosition].categoryName -> false
            oldList[oldItemPosition].color != newList[newItemPosition].color -> false
            oldList[oldItemPosition].image != newList[newItemPosition].image -> false
            oldList[oldItemPosition].percent != newList[newItemPosition].percent -> false
            oldList[oldItemPosition].total != newList[newItemPosition].total -> false
            else -> true
        }
    }

}
