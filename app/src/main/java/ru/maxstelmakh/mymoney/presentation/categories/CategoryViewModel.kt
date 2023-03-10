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
}
