package ru.maxstelmakh.mymoney.domain.usecases

import ru.maxstelmakh.mymoney.data.localrepository.cashrepository.EventsRepositoryImpl
import ru.maxstelmakh.mymoney.domain.model.EventModelDomain
import javax.inject.Inject

class DeleteEventUseCase @Inject constructor(
    private val eventsRepositoryImpl: EventsRepositoryImpl
) {
    suspend operator fun invoke(eventModelDomain: EventModelDomain){
        eventsRepositoryImpl.deleteEvent(event = eventModelDomain)
    }
}