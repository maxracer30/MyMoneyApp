package ru.maxstelmakh.mymoney.presentation.categories

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import ru.maxstelmakh.mymoney.domain.model.CategoryModelDomain
import ru.maxstelmakh.mymoney.domain.usecases.categoriesusecases.GetAllCategoriesUseCase
import javax.inject.Inject

@HiltViewModel
class CategoryViewModel @Inject constructor(
    private val getAllCategoriesUseCase: GetAllCategoriesUseCase
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

//    fun deleteEvent(eventModelDomain: EventModelDomain) {
//        viewModelScope.launch(Dispatchers.IO) {
//            deleteEventUseCase(eventModelDomain = eventModelDomain)
//        }
//    }

//    fun addEvent(event: EventModelDomain) {
//        try {
//            viewModelScope.launch(Dispatchers.IO) {
//                saveNewEventUseCase(
//                    eventModelDomain = EventModelDomain(
//                        id = event.id,
//                        expense = event.expense,
//                        description = event.description,
//                        category = event.category,
//                        joined_date = event.joined_date
//                    )
//                )
//            }
//        } catch (nullPointerException: NullPointerException) {
//            Log.d("StatesOfApp", "$nullPointerException")
//        }
//    }

}
