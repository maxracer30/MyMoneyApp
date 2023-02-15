package ru.maxstelmakh.mymoney.presentation.addevent

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import ru.maxstelmakh.mymoney.domain.model.EventModelDomain
import ru.maxstelmakh.mymoney.domain.usecases.eventusecases.SaveNewEventUseCase
import javax.inject.Inject

@HiltViewModel
class AddNewEventViewModel @Inject constructor(
    private val saveNewEventUseCase: SaveNewEventUseCase
) : ViewModel() {

    fun insert(eventModelDomain: EventModelDomain) =
        viewModelScope.launch(Dispatchers.IO) {
            saveNewEventUseCase(eventModelDomain)
        }
}