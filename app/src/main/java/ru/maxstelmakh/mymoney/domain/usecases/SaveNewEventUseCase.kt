package ru.maxstelmakh.mymoney.domain.usecases

import ru.maxstelmakh.mymoney.data.localrepository.cashrepository.EventsRepositoryImpl
import ru.maxstelmakh.mymoney.domain.model.EventModelDomain
import ru.maxstelmakh.mymoney.presentation.addevent.SaveEventParams
import javax.inject.Inject

class SaveNewEventUseCase @Inject constructor(
    private val saveEventParams: SaveEventParams,
    private val eventsRepositoryImpl: EventsRepositoryImpl
) {
    suspend operator fun invoke() {
        eventsRepositoryImpl.insertEvent(
            EventModelDomain(
                title = saveEventParams.title,
                expense = saveEventParams.expense,
                description = saveEventParams.description,
                category = saveEventParams.category
            )
        )
    }
}