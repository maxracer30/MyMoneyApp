package ru.maxstelmakh.mymoney.data.models

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "category")
data class CategoryModelData(
    @PrimaryKey(autoGenerate = false)
    var category: String,
    var color: String
)
