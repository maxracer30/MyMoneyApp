package ru.maxstelmakh.mymoney.presentation.details

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import ru.maxstelmakh.mymoney.domain.model.EventModelDomain
import ru.maxstelmakh.mymoney.domain.usecases.eventusecases.UpdateEventUseCase
import javax.inject.Inject

@HiltViewModel
class DetailsViewModel @Inject constructor(
    private val updateEventUseCase: UpdateEventUseCase
) : ViewModel() {

    fun update(eventModelDomain: EventModelDomain) {
        viewModelScope.launch(Dispatchers.IO) {
            updateEventUseCase(eventModelDomain = eventModelDomain)
            Log.d("StatesOfApp", "in detVM ${eventModelDomain.id.toString()}")
        }
    }
}