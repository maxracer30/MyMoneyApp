package ru.maxstelmakh.mymoney.presentation.adapter.categoriesadapter

import android.annotation.SuppressLint
import android.graphics.Color
import android.graphics.PorterDuff
import androidx.recyclerview.widget.RecyclerView
import ru.maxstelmakh.mymoney.databinding.CategoryItemLayoutBinding
import ru.maxstelmakh.mymoney.domain.model.CategoryModelDomain
import ru.maxstelmakh.mymoney.presentation.adapter.listeners.CategoryListener

class CategoriesViewHolder(
    private val categoryBinding: CategoryItemLayoutBinding
) : RecyclerView.ViewHolder(categoryBinding.root) {

    @Suppress("DEPRECATION")
    @SuppressLint("ResourceAsColor")
    fun refreshList(categoryModelDomain: CategoryModelDomain, listener: CategoryListener) {
        with (categoryBinding) {

            eventCategory.text = categoryModelDomain.categoryName

            categoryCard.background
                .mutate()
                .setColorFilter(
                    Color.parseColor(categoryModelDomain.color),
                    PorterDuff.Mode.SRC_ATOP)

            imageCategory.setImageResource(categoryModelDomain.image)

            itemView.setOnClickListener {
                if (categoryModelDomain.categoryId != 1) {
                    listener.onClick(categoryModelDomain)
                }
            }
        }
    }
}
