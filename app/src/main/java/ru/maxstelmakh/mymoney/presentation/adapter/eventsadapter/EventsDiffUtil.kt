package ru.maxstelmakh.mymoney.presentation.adapter.eventsadapter

import androidx.recyclerview.widget.DiffUtil
import ru.maxstelmakh.mymoney.domain.model.EventInDetailModelDomain

class EventsDiffUtil(
    private val oldList: List<EventInDetailModelDomain>,
    private val newList: List<EventInDetailModelDomain>
) : DiffUtil.Callback() {

    override fun getOldListSize() = oldList.size

    override fun getNewListSize() = newList.size

    override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        return oldList[oldItemPosition].eventId == newList[newItemPosition].eventId
    }

    override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        return when {
            oldList[oldItemPosition].eventId != newList[newItemPosition].eventId -> false
            oldList[oldItemPosition].expense != newList[newItemPosition].expense -> false
            oldList[oldItemPosition].categoryName != newList[newItemPosition].categoryName -> false
            oldList[oldItemPosition].description != newList[newItemPosition].description -> false
            oldList[oldItemPosition].color != newList[newItemPosition].color -> false
//            oldList[oldItemPosition].joined_date != newList[newItemPosition].joined_date -> false
            else -> true
        }
    }
}