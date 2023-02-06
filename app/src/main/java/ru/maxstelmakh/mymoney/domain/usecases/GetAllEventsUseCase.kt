package ru.maxstelmakh.mymoney.domain.usecases

import ru.maxstelmakh.mymoney.domain.repository.EventsRepositoryDao
import javax.inject.Inject

class GetAllEventsUseCase @Inject constructor(
    private val eventsRepository: EventsRepositoryDao
) {
    suspend operator fun invoke() = eventsRepository.getAllEvents()
}