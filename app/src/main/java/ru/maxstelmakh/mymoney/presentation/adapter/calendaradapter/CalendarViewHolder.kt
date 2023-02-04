package ru.maxstelmakh.mymoney.presentation.adapter.calendaradapter

import android.R
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import ru.maxstelmakh.mymoney.presentation.adapter.calendaradapter.CalendarAdapter.OnItemListener


class CalendarViewHolder(
    itemView: View,
    onItemListener: OnItemListener,
    days: ArrayList<LocalDate>
) :
    RecyclerView.ViewHolder(itemView), View.OnClickListener {
    private val days: ArrayList<LocalDate>
    val parentView: View
    val dayOfMonth: TextView
    private val onItemListener: OnItemListener

    init {
        parentView = itemView.findViewById(R.id.parentView)
        dayOfMonth = itemView.findViewById(R.id.cellDayText)
        this.onItemListener = onItemListener
        itemView.setOnClickListener(this)
        this.days = days
    }

    fun onClick(view: View?) {
        onItemListener.onItemClick(adapterPosition, days[adapterPosition])
    }
}