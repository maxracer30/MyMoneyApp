package ru.maxstelmakh.mymoney.domain.usecases

import ru.maxstelmakh.mymoney.data.localrepository.cashrepository.EventsRepositoryImpl
import javax.inject.Inject

class GetAllEventsUseCase @Inject constructor(
    private val eventsRepository: EventsRepositoryImpl
) {
    suspend operator fun invoke() = eventsRepository.getAllEvents()
}