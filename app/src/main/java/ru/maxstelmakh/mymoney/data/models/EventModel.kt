package ru.maxstelmakh.mymoney.data.models

data class EventModel(
    var id: Long = 0,
    var title: String,
    var giveOrTake: Boolean,
    var description: String,
    var category: String,
    var account: String,
    )
