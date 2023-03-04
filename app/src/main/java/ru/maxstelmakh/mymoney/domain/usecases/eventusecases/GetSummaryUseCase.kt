package ru.maxstelmakh.mymoney.domain.usecases.eventusecases

import kotlinx.coroutines.flow.Flow
import ru.maxstelmakh.mymoney.data.repository.cashrepository.EventsRepository
import javax.inject.Inject

class GetSummaryUseCase @Inject constructor(
    private val repository: EventsRepository
) {
    suspend operator fun invoke(): Flow<List<Int>> {
       return repository.getSumInMonth()
    }
}

