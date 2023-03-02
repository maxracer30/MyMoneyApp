package ru.maxstelmakh.mymoney.domain.usecases.eventusecases

import ru.maxstelmakh.mymoney.data.repository.cashrepository.EventsRepositoryImpl
import ru.maxstelmakh.mymoney.domain.model.EventModelDomain
import javax.inject.Inject

class UpdateEventUseCase @Inject constructor(
    private val eventRepository: EventsRepositoryImpl
) {
    suspend operator fun invoke(eventModelDomain: EventModelDomain) {
        eventRepository.updateEvent(event = eventModelDomain)
    }
}
