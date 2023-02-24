package ru.maxstelmakh.mymoney.presentation.adapter.listeners

import ru.maxstelmakh.mymoney.domain.model.EventInDetailModelDomain

interface EventsListener {
    fun onClick(eventModelDomain: EventInDetailModelDomain)
}
