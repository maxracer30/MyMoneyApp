package ru.maxstelmakh.mymoney.presentation.adapter.eventsadapter

import android.graphics.Color
import android.graphics.PorterDuff
import androidx.recyclerview.widget.RecyclerView
import ru.maxstelmakh.mymoney.databinding.EventItemLayoutBinding
import ru.maxstelmakh.mymoney.domain.model.EventInDetailModelDomain
import ru.maxstelmakh.mymoney.presentation.adapter.listeners.EventsListener

class EventsViewHolder(
    private val eventBinding: EventItemLayoutBinding,
) : RecyclerView.ViewHolder(eventBinding.root) {
    @Suppress("DEPRECATION")
    fun refreshList(event: EventInDetailModelDomain, listener: EventsListener) {

        with(eventBinding) {

            eventCategory
                .background
                .mutate()
                .setColorFilter(
                    Color.parseColor(event.color),
                    PorterDuff.Mode.SRC_ATOP
                )
            eventCategory.text = event.categoryName
            eventDescription.text = event.description
            eventExpense.text = event.expense.toString()

            root.setOnClickListener {
                listener.onClick(event)
            }
        }
    }

}