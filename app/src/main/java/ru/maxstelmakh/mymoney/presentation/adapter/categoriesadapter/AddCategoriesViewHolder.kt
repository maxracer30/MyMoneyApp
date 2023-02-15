package ru.maxstelmakh.mymoney.presentation.adapter.categoriesadapter

import androidx.recyclerview.widget.RecyclerView
import ru.maxstelmakh.mymoney.databinding.AddCategoryItemLayoutBinding
import ru.maxstelmakh.mymoney.presentation.adapter.listeners.AddCategoryListener

class AddCategoriesViewHolder(
    private val binding: AddCategoryItemLayoutBinding
) : RecyclerView.ViewHolder(binding.root) {
    fun bind(listener: AddCategoryListener) {
        binding.categoryCard.setOnClickListener {
            listener.onAddClick()
        }
    }
}
