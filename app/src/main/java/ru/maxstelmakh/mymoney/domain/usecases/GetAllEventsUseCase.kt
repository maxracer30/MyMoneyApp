package ru.maxstelmakh.mymoney.domain.usecases

import android.util.Log
import ru.maxstelmakh.mymoney.data.localrepository.cashrepository.EventsRepositoryImpl
import ru.maxstelmakh.mymoney.domain.model.EventModelDomain
import javax.inject.Inject

class GetAllEventsUseCase @Inject constructor(
    private val eventsRepositoryImpl: EventsRepositoryImpl
) {
    suspend fun invoke(): List<EventModelDomain>{
        val list = eventsRepositoryImpl.getAllEvents()
        Log.d("StatesOfApp", "listInUseCase = ${list.size}")
        return list
    }
}