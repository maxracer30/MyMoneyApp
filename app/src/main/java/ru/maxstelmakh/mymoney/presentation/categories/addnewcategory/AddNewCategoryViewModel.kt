package ru.maxstelmakh.mymoney.presentation.categories.addnewcategory

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import ru.maxstelmakh.mymoney.domain.model.CategoryModelDomain
import ru.maxstelmakh.mymoney.domain.usecases.categoriesusecases.AddNewCategoryUseCase
import javax.inject.Inject

class AddNewCategoryViewModel @Inject constructor(
    private val addNewCategoryUseCase: AddNewCategoryUseCase
) : ViewModel() {


    fun addNewCategory(icon: Int, categoryColor: String, name: String) {
        viewModelScope.launch(Dispatchers.IO) {
            addNewCategoryUseCase(
                categoryModelDomain = CategoryModelDomain(
                    category = name,
                    color = categoryColor,
                    image = icon
                )
            )
        }
    }
}