package ru.maxstelmakh.mymoney.presentation.adapter.eventsadapter

import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import ru.maxstelmakh.mymoney.databinding.EventItemLayoutBinding
import ru.maxstelmakh.mymoney.domain.model.EventModelDomain

class EventsViewHolder(
    private val eventBinding: EventItemLayoutBinding
) : RecyclerView.ViewHolder(eventBinding.root) {
    fun refreshList(event: EventModelDomain) {
        eventBinding.apply {
            eventTitle.text = event.title
            eventCategory.text = event.category
            eventDescription.text = event.description
            eventExpense.text = event.expense.toString()

            root.setOnClickListener {
                Toast.makeText(it.context, "${event.id}", Toast.LENGTH_SHORT).show()
            }
        }
    }

}