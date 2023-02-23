package ru.maxstelmakh.mymoney.data.models

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class CategoryModelData(
    @PrimaryKey(autoGenerate = true)
    var categoryId: Int = 0,
    var categoryName: String,
    var color: String,
    var image: Int
)
