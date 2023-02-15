package ru.maxstelmakh.mymoney.presentation.adapter.eventsadapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import ru.maxstelmakh.mymoney.databinding.EventItemLayoutBinding
import ru.maxstelmakh.mymoney.domain.model.EventModelDomain

class EventsAdapter: RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    var oldEventsList = emptyList<EventModelDomain>()


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val view =
            EventItemLayoutBinding
                .inflate(LayoutInflater.from(parent.context), parent, false)
        return EventsViewHolder(view)
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        (holder as EventsViewHolder).refreshList(oldEventsList[position])
    }

    override fun getItemCount() = oldEventsList.size

    fun setList(newEventsList: List<EventModelDomain>) {
        val diffUtil = EventsDiffUtil(oldEventsList, newEventsList)
        val diffResults = DiffUtil.calculateDiff(diffUtil)
        oldEventsList = newEventsList
        diffResults.dispatchUpdatesTo(this)
    }
}