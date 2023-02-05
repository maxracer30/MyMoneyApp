package ru.maxstelmakh.mymoney.presentation.adapter.eventsadapter

import androidx.recyclerview.widget.RecyclerView
import ru.maxstelmakh.mymoney.databinding.EventItemLayoutBinding
import ru.maxstelmakh.mymoney.domain.model.EventModelDomain

class EventsViewHolder(
    private val eventBinding: EventItemLayoutBinding
) : RecyclerView.ViewHolder(eventBinding.root) {
    fun refreshList(event: EventModelDomain) {
        eventBinding.eventTitle.text = event.title
    }
}