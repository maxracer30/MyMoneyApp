package ru.maxstelmakh.mymoney.presentation.main

import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import ru.maxstelmakh.mymoney.domain.usecases.GetAllEventsUseCase
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val getAllEventsUseCase: GetAllEventsUseCase
) : ViewModel() {

//    private val _events = MutableLiveData<List<EventModelDomain>>()
//    val events: LiveData<List<EventModelDomain>>
//        get() = _events
//
//    init {
//        getAllEvents()
//    }
//
//
//    private fun getAllEvents() {
//        viewModelScope.launch {
//            getAllEventsUseCase().let {
//                _events.postValue(it)
//            }
//        }
//    }
}