package ru.maxstelmakh.mymoney.presentation.details

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import ru.maxstelmakh.mymoney.domain.model.CategoryModelDomain
import ru.maxstelmakh.mymoney.domain.model.EventModelDomain
import ru.maxstelmakh.mymoney.domain.usecases.categoriesusecases.GetAllCategoriesUseCase
import ru.maxstelmakh.mymoney.domain.usecases.eventusecases.UpdateEventUseCase
import javax.inject.Inject

@HiltViewModel
class DetailsViewModel @Inject constructor(
    private val getAllCategoriesUseCase: GetAllCategoriesUseCase,
    private val updateEventUseCase: UpdateEventUseCase
) : ViewModel() {

    private val _categories = MutableLiveData<List<CategoryModelDomain>>()
    val categories: LiveData<List<CategoryModelDomain>> = _categories


    init {
        viewModelScope.launch {
            getAllCategoriesUseCase().collect {
                _categories.postValue(it)
            }
        }
    }
    fun update(eventModelDomain: EventModelDomain) {
        viewModelScope.launch(Dispatchers.IO) {
            updateEventUseCase(eventModelDomain = eventModelDomain)
        }
    }
}