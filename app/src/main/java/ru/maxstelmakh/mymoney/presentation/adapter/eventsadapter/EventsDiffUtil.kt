package ru.maxstelmakh.mymoney.presentation.adapter.eventsadapter

import androidx.recyclerview.widget.DiffUtil
import ru.maxstelmakh.mymoney.domain.model.EventModelDomain

class EventsDiffUtil(
    private val oldList: List<EventModelDomain>,
    private val newList: List<EventModelDomain>
): DiffUtil.Callback() {

    override fun getOldListSize() = oldList.size

    override fun getNewListSize() = newList.size

    override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        return oldList[oldItemPosition].id == newList[newItemPosition].id
    }

    override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        return when {
            oldList[oldItemPosition].id != newList[newItemPosition].id -> false
            oldList[oldItemPosition].expense != newList[newItemPosition].expense -> false
//            oldList[oldItemPosition].category != newList[newItemPosition].category -> false
            oldList[oldItemPosition].description != newList[newItemPosition].description -> false
//            oldList[oldItemPosition].joined_date != newList[newItemPosition].joined_date -> false
            else -> true
        }
    }
}