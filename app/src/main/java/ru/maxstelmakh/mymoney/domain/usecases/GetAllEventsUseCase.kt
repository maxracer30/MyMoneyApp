package ru.maxstelmakh.mymoney.domain.usecases

import kotlinx.coroutines.flow.Flow
import ru.maxstelmakh.mymoney.data.repository.cashrepository.EventsRepository
import ru.maxstelmakh.mymoney.domain.model.EventModelDomain
import javax.inject.Inject

class GetAllEventsUseCase @Inject constructor(
    private val eventsRepositoryImpl: EventsRepository
) {
    suspend operator fun invoke(): Flow<List<EventModelDomain>> {
        return eventsRepositoryImpl.getAllEvents()
    }
}