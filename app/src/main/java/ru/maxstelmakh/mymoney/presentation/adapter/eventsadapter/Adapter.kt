package ru.maxstelmakh.mymoney.presentation.adapter.eventsadapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import ru.maxstelmakh.mymoney.databinding.EventItemLayoutBinding
import ru.maxstelmakh.mymoney.domain.model.EventModelDomain

class Adapter: RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private var eventsList = emptyList<EventModelDomain>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val view =
            EventItemLayoutBinding
                .inflate(LayoutInflater.from(parent.context), parent, false)
        return EventsViewHolder(view)
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        (holder as EventsViewHolder).refreshList(eventsList[position])
    }

    @SuppressLint("NotifyDataSetChanged")
    fun setList(list: List<EventModelDomain>){
        eventsList = list
        notifyDataSetChanged()
    }

    override fun getItemCount(): Int {
        return eventsList.size
    }
}