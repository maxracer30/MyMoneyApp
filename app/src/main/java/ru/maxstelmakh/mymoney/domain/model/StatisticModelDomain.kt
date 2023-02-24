package ru.maxstelmakh.mymoney.domain.model

data class StatisticModelDomain(
    val categoryId: Long,
    val categoryName: String,
    val color: String,
    val image: Int,
    val total: Long,
    val percent: Float
)
