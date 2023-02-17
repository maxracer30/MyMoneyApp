package ru.maxstelmakh.mymoney.domain.model

data class CategoryModelDomain(
    var categoryId: Long = 0,
    var category: String,
    var color: String,
    var image: Int
)