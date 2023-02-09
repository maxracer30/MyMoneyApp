package ru.maxstelmakh.mymoney.presentation.main

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import ru.maxstelmakh.mymoney.domain.model.EventModelDomain
import ru.maxstelmakh.mymoney.domain.usecases.DeleteEventUseCase
import ru.maxstelmakh.mymoney.domain.usecases.GetAllEventsUseCase
import ru.maxstelmakh.mymoney.domain.usecases.SaveNewEventUseCase
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val getAllEventsUseCase: GetAllEventsUseCase,
    private val deleteEventUseCase: DeleteEventUseCase,
    private val saveNewEventUseCase: SaveNewEventUseCase
) : ViewModel() {


    private val _events = MutableSharedFlow<List<EventModelDomain>>(replay = 1)
    val events: SharedFlow<List<EventModelDomain>> = _events.asSharedFlow()


    init {
        viewModelScope.launch {
            _events.emitAll(
                flow {
                    emit(getAllEventsUseCase())
                }
            )
        }
    }

    fun deleteEvent(eventModelDomain: EventModelDomain) {
        viewModelScope.launch(Dispatchers.IO) {
            deleteEventUseCase(eventModelDomain = eventModelDomain)
        }
    }

    fun addEvent(event: EventModelDomain) {
        try {
            viewModelScope.launch(Dispatchers.IO) {
                saveNewEventUseCase(eventModelDomain = EventModelDomain(
                    id = event.id,
                    title = event.title,
                    expense = event.expense,
                    description = event.description,
                    category = event.category
                )
                )
            }
        } catch (nullPointerException: NullPointerException) {
            Log.d("StatesOfApp", "$nullPointerException")
        }
    }

}