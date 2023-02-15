package ru.maxstelmakh.mymoney.presentation.adapter.listeners

import ru.maxstelmakh.mymoney.domain.model.EventModelDomain

interface EventsListener {
    fun onClick(eventModelDomain: EventModelDomain)
}
