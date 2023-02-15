package ru.maxstelmakh.mymoney.presentation.adapter.categoriesadapter

import androidx.recyclerview.widget.DiffUtil
import ru.maxstelmakh.mymoney.domain.model.CategoryModelDomain

class CategoriesDiffUtil(
    private val oldList: List<CategoryModelDomain>,
    private val newList: List<CategoryModelDomain>
) : DiffUtil.Callback() {
    override fun getOldListSize() = oldList.size

    override fun getNewListSize() = newList.size

    override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        return oldList[oldItemPosition].category == newList[newItemPosition].category
    }

    override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        return when {
            oldList[oldItemPosition].category != newList[newItemPosition].category -> false
            else -> true
        }
    }

}
