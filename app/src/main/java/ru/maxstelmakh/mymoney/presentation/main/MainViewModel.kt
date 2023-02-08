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
import ru.maxstelmakh.mymoney.domain.usecases.GetAllEventsUseCase
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val getAllEventsUseCase: GetAllEventsUseCase,
) : ViewModel() {


    private val _events = MutableLiveData<List<EventModelDomain>>()
    val events: LiveData<List<EventModelDomain>>
        get() = _events


    init {
        viewModelScope.launch(Dispatchers.Main) { getAllEvents() }
    }

    fun getEvents() {
        viewModelScope.launch(Dispatchers.Main) {
            getAllEvents()
        }
    }

    private suspend fun getAllEvents() {
        viewModelScope.launch(Dispatchers.Main) {  }
            getAllEventsUseCase.invoke().let {
                    Log.d("StatesOfApp", "let = ${it.size}")
                    _events.value = it
                    Log.d("StatesOfApp", "_events = ${_events.value?.size}, events = ${events.value?.size}")
            }
    }
}