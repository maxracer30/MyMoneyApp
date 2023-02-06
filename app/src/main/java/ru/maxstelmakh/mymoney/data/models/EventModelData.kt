package ru.maxstelmakh.mymoney.data.models

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class EventModelData(
    @PrimaryKey(autoGenerate = true)
    var primaryKey: Long = 0,
    var title: String,
    var expense: Int,
    var description: String,
    var category: String,
    )
