package ru.maxstelmakh.mymoney.domain.usecases.eventusecases

import kotlinx.coroutines.flow.Flow
import ru.maxstelmakh.mymoney.data.repository.cashrepository.EventsRepository
import ru.maxstelmakh.mymoney.domain.model.EventInDetailModelDomain
import javax.inject.Inject

class GetAllEventsUseCase @Inject constructor(
    private val eventsRepositoryImpl: EventsRepository
) {
    suspend operator fun invoke(): Flow<List<EventInDetailModelDomain>> {
        return eventsRepositoryImpl.getAllEventsInDetail()
    }
}