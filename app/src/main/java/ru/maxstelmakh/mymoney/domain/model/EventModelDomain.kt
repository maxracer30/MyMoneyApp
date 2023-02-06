package ru.maxstelmakh.mymoney.domain.model

data class EventModelDomain(
    var id: Long = 0,
    var title: String,
    var expense: Int,
    var description: String,
    var category: String,
)
