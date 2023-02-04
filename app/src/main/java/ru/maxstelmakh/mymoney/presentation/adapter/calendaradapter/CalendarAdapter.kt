package ru.maxstelmakh.mymoney.presentation.adapter.calendaradapter

import android.R
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import sun.util.calendar.CalendarUtils
import java.lang.String
import java.time.LocalDate
import kotlin.Int


internal class CalendarAdapter(days: ArrayList<LocalDate>, onItemListener: OnItemListener) :
    RecyclerView.Adapter<CalendarViewHolder>() {
    private val days: ArrayList<LocalDate>
    private val onItemListener: OnItemListener

    init {
        this.days = days
        this.onItemListener = onItemListener
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CalendarViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val view: View = inflater.inflate(R.layout.calendar_cell, parent, false)
        val layoutParams: ViewGroup.LayoutParams = view.getLayoutParams()
        if (days.size() > 15) //month view
            layoutParams.height = (parent.height * 0.166666666).toInt() else  // week view
            layoutParams.height = parent.height
        return CalendarViewHolder(view, onItemListener, days)
    }

    override fun onBindViewHolder(holder: CalendarViewHolder, position: Int) {
        val date: LocalDate = days[position]
        if (date == null) holder.dayOfMonth.setText("") else {
            holder.dayOfMonth.setText(String.valueOf(date.getDayOfMonth()))
            if (date.equals(sun.util.calendar.CalendarUtils.selectedDate)) holder.parentView.setBackgroundColor(
                Color.LTGRAY
            )
        }
    }

    override fun getItemCount(): Int {
        return days.size()
    }

    interface OnItemListener {
        fun onItemClick(position: Int, date: LocalDate?)
    }
}