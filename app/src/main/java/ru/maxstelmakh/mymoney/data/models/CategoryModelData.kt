package ru.maxstelmakh.mymoney.data.models

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class CategoryModelData(
    @PrimaryKey(autoGenerate = false)
    var category: String,
    var color: String,
    var image: Int
)
