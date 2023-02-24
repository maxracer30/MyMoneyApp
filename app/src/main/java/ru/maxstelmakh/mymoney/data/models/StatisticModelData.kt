package ru.maxstelmakh.mymoney.data.models

data class StatisticModelData(
    val categoryId: Long,
    val categoryName: String,
    val color: String,
    val image: Int,
    val total: Long,
    val percent: Float
)
