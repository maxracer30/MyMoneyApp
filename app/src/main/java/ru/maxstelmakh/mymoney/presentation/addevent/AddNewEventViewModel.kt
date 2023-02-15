package ru.maxstelmakh.mymoney.presentation.addevent

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
import ru.maxstelmakh.mymoney.domain.usecases.eventusecases.SaveNewEventUseCase
import javax.inject.Inject

@HiltViewModel
class AddNewEventViewModel @Inject constructor(
    private val getAllCategoriesUseCase: GetAllCategoriesUseCase,
    private val saveNewEventUseCase: SaveNewEventUseCase
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

    fun insert(eventModelDomain: EventModelDomain) =
        viewModelScope.launch(Dispatchers.IO) {
            saveNewEventUseCase(eventModelDomain)
        }
}

