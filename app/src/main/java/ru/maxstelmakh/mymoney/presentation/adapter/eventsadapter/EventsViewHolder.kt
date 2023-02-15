package ru.maxstelmakh.mymoney.presentation.adapter.eventsadapter

import androidx.recyclerview.widget.RecyclerView
import ru.maxstelmakh.mymoney.databinding.EventItemLayoutBinding
import ru.maxstelmakh.mymoney.domain.model.EventModelDomain
import ru.maxstelmakh.mymoney.presentation.adapter.listeners.EventsListener

class EventsViewHolder(
    private val eventBinding: EventItemLayoutBinding,
) : RecyclerView.ViewHolder(eventBinding.root) {
    fun refreshList(event: EventModelDomain, listener: EventsListener) {

        with(eventBinding) {

            eventCategory.text = event.category
            eventDescription.text = event.description
            eventExpense.text = event.expense.toString()

            root.setOnClickListener {
                listener.onClick(event)
            }
        }
    }

}