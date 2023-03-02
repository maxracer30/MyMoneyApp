package ru.maxstelmakh.mymoney.presentation.categories.changecategory

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import ru.maxstelmakh.mymoney.R
import ru.maxstelmakh.mymoney.domain.model.CategoryModelDomain
import ru.maxstelmakh.mymoney.domain.usecases.categoriesusecases.ChangeCategoryUseCase
import ru.maxstelmakh.mymoney.domain.usecases.categoriesusecases.DeleteCategoryUseCase
import javax.inject.Inject


@HiltViewModel
class ChangeCategoryViewModel @Inject constructor(
    private val changeCategoryUseCase: ChangeCategoryUseCase,
    private val deleteCategoryUseCase: DeleteCategoryUseCase
) : ViewModel() {


    lateinit var categoryToChange: CategoryModelDomain
    var selectedImage: Int = R.drawable.baseline_question_mark_24
    var changedColor: String = ""
    var changedName: String = ""


    fun changeCategory() {
        viewModelScope.launch(Dispatchers.IO) {
            changeCategoryUseCase(
                categoryModelDomain = CategoryModelDomain(
                    categoryId = categoryToChange.categoryId,
                    categoryName = changedName,
                    color = changedColor,
                    image = selectedImage
                )
            )
        }
    }

    fun deleteCategory() {
        viewModelScope.launch(Dispatchers.IO) {
            deleteCategoryUseCase(categoryToChange)
        }
    }
}