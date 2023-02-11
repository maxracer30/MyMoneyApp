package ru.maxstelmakh.mymoney.presentation.main

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
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

    private val _events = MutableLiveData<List<EventModelDomain>>()
    val events: LiveData<List<EventModelDomain>> = _events

    init {
        viewModelScope.launch {
            getAllEventsUseCase().collect {
                _events.postValue(it)
            }
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
                saveNewEventUseCase(
                    eventModelDomain = EventModelDomain(
                        id = event.id,
                        expense = event.expense,
                        description = event.description,
                        category = event.category,
                        joined_date = event.joined_date
                    )
                )
            }
        } catch (nullPointerException: NullPointerException) {
            Log.d("StatesOfApp", "$nullPointerException")
        }
    }

}