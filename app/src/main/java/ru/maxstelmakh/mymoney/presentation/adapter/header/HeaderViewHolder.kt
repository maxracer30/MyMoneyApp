package ru.maxstelmakh.mymoney.presentation.adapter.header

import android.content.Context
import android.widget.FrameLayout
import android.widget.TextView
import ru.maxstelmakh.mymoney.R

class HeaderViewHolder(
    context: Context
) : FrameLayout(context) {
    private lateinit var headerDate: TextView

    init {
        inflate(context, R.layout.header_item_layout, this)
        findView()
    }

    private fun findView() {
        headerDate = findViewById(R.id.headerDate)
    }

    fun setDate(dateString: String) {
        headerDate.text = dateString
    }
}