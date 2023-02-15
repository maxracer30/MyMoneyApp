package ru.maxstelmakh.mymoney.presentation.adapter.categoriesadapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import ru.maxstelmakh.mymoney.R
import ru.maxstelmakh.mymoney.databinding.AddCategoryItemLayoutBinding
import ru.maxstelmakh.mymoney.databinding.CategoryItemLayoutBinding
import ru.maxstelmakh.mymoney.domain.model.CategoryModelDomain
import ru.maxstelmakh.mymoney.presentation.adapter.listeners.AddCategoryListener
import ru.maxstelmakh.mymoney.presentation.adapter.listeners.CategoryListener

class CategoriesAdapter(
    private val listener: CategoryListener,
    private val addListener: AddCategoryListener,
) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private var oldCategoriesList = emptyList<CategoryModelDomain>()


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return when (viewType) {
            R.layout.category_item_layout -> {
                val view =
                    CategoryItemLayoutBinding.inflate(
                        LayoutInflater
                            .from(parent.context), parent, false
                    )
                CategoriesViewHolder(view)
            }
            R.layout.add_category_item_layout -> {
                val view =
                    AddCategoryItemLayoutBinding.inflate(
                        LayoutInflater
                            .from(parent.context), parent, false
                    )
                AddCategoriesViewHolder(view)
            }
            else -> throw IllegalArgumentException("Unknown type $viewType")
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when (getItemViewType(position = position)) {
            R.layout.category_item_layout ->  {
                (holder as CategoriesViewHolder)
                    .refreshList(oldCategoriesList[position], listener)
            }
            R.layout.add_category_item_layout -> {
                (holder as AddCategoriesViewHolder)
                    .bind(addListener)
            }
        }
    }

    override fun getItemCount() = oldCategoriesList.size + 1

    override fun getItemViewType(position: Int): Int {
        return when (position) {
            oldCategoriesList.size -> R.layout.add_category_item_layout
            else -> R.layout.category_item_layout
        }

    }

    fun setList(newCategoriesList: List<CategoryModelDomain>) {
        val diffUtil = CategoriesDiffUtil(oldCategoriesList, newCategoriesList)
        val diffResults = DiffUtil.calculateDiff(diffUtil)
        oldCategoriesList = newCategoriesList
        diffResults.dispatchUpdatesTo(this)
    }
}