package ru.maxstelmakh.mymoney.presentation.addnewcategory

import android.widget.ImageView
import androidx.lifecycle.ViewModel
import androidx.recyclerview.widget.RecyclerView
import ru.maxstelmakh.mymoney.domain.usecases.categoriesusecases.AddNewCategoryUseCase
import javax.inject.Inject

class AddNewCategoryViewModel @Inject constructor(
    addNewCategoryUseCase: AddNewCategoryUseCase
): ViewModel() {
    fun addNewCategory(icon: ImageView, categoryColor: RecyclerView, name: String) {

    }
}