package ru.maxstelmakh.mymoney.data.relations

import androidx.room.Entity

@Entity(primaryKeys = ["category", "eventId"])
data class CategoryEventCrossRef(
    val category: String,
    val eventId: Long
)
