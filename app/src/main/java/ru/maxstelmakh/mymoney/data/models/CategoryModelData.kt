package ru.maxstelmakh.mymoney.data.models

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class CategoryModelData(
    @PrimaryKey(autoGenerate = true)
    var categoryId: Long = 0,
    var category: String,
    var color: String,
    var image: Int
)
