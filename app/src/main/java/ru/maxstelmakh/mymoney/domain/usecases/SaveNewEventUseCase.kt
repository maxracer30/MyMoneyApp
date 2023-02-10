package ru.maxstelmakh.mymoney.domain.usecases

import android.annotation.SuppressLint
import ru.maxstelmakh.mymoney.data.repository.cashrepository.EventsRepositoryImpl
import ru.maxstelmakh.mymoney.domain.model.EventModelDomain
import javax.inject.Inject

class SaveNewEventUseCase @Inject constructor(
    private val eventsRepositoryImpl: EventsRepositoryImpl
) {
    @SuppressLint("NewApi")
    suspend operator fun invoke(eventModelDomain: EventModelDomain) {
        eventsRepositoryImpl.insertEvent(
            EventModelDomain(
                title = eventModelDomain.title,
                expense = eventModelDomain.expense,
                description = eventModelDomain.description,
                category = eventModelDomain.category,
                joined_date = eventModelDomain.joined_date
            )
        )
    }
}