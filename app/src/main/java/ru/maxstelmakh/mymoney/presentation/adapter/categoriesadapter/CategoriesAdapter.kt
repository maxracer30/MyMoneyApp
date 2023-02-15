package ru.maxstelmakh.mymoney.presentation.adapter.categoriesadapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import ru.maxstelmakh.mymoney.databinding.CategoryItemLayoutBinding
import ru.maxstelmakh.mymoney.domain.model.CategoryModelDomain

class CategoriesAdapter : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private var oldCategoriesList = emptyList<CategoryModelDomain>()


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val view =
            CategoryItemLayoutBinding
                .inflate(LayoutInflater.from(parent.context), parent, false)
        return CategoriesViewHolder(view)
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        (holder as CategoriesViewHolder).refreshList(oldCategoriesList[position])
    }

    override fun getItemCount() = oldCategoriesList.size

    fun setList(newCategoriesList: List<CategoryModelDomain>) {
        val diffUtil = CategoriesDiffUtil(oldCategoriesList, newCategoriesList)
        val diffResults = DiffUtil.calculateDiff(diffUtil)
        oldCategoriesList = newCategoriesList
        diffResults.dispatchUpdatesTo(this)
    }
}