package ru.maxstelmakh.mymoney.domain.usecases.eventusecases

import android.util.Log
import ru.maxstelmakh.mymoney.data.repository.cashrepository.EventsRepositoryImpl
import ru.maxstelmakh.mymoney.domain.model.EventModelDomain
import javax.inject.Inject

class UpdateEventUseCase @Inject constructor(
    private val eventRepository: EventsRepositoryImpl
) {
    suspend operator fun invoke(eventModelDomain: EventModelDomain) {
        Log.d("StatesOfApp", "in detFrag ${eventModelDomain.id.toString()}")
        eventRepository.updateEvent(event = eventModelDomain)
    }
}
