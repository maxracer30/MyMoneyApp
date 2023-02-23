package ru.maxstelmakh.mymoney.domain.model

data class CategoryModelDomain(
    var categoryId: Int = 0,
    var categoryName: String,
    var color: String,
    var image: Int
)