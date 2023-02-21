package ru.maxstelmakh.mymoney.presentation.main

import android.annotation.SuppressLint
import android.util.Log
import androidx.core.graphics.*
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import ru.maxstelmakh.mymoney.domain.model.CategoryModelDomain
import ru.maxstelmakh.mymoney.domain.model.EventModelDomain
import ru.maxstelmakh.mymoney.domain.usecases.categoriesusecases.AddNewCategoryUseCase
import ru.maxstelmakh.mymoney.domain.usecases.eventusecases.DeleteEventUseCase
import ru.maxstelmakh.mymoney.domain.usecases.eventusecases.GetAllEventsUseCase
import ru.maxstelmakh.mymoney.domain.usecases.eventusecases.SaveNewEventUseCase
import java.util.*
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val getAllEventsUseCase: GetAllEventsUseCase,
    private val deleteEventUseCase: DeleteEventUseCase,
    private val saveNewEventUseCase: SaveNewEventUseCase,
    private val addNewCategoryUseCase: AddNewCategoryUseCase
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

    @SuppressLint("NewApi")
    fun firstStart(titles: List<String>, colors: List<String>, images: List<Int>) {
        viewModelScope.launch(Dispatchers.IO) {
            for (i in 0..6) {
                addNewCategoryUseCase(
                    categoryModelDomain =
                    CategoryModelDomain(
                        category = titles[i],
                        color = colors[i],
                        image = images[i]
                    )
                )
            }
        }
    }
}