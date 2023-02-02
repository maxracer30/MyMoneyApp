package ru.maxstelmakh.mymoney.domain.model

data class EventModelDomain(
    var id: Long = 0,
    var title: String,
    var isExpense: Boolean,
    var description: String,
    var category: String,
    var account: String,
)
