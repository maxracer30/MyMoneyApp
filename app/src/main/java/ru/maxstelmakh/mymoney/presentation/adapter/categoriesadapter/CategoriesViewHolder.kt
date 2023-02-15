package ru.maxstelmakh.mymoney.presentation.adapter.categoriesadapter

import android.annotation.SuppressLint
import android.graphics.Color
import android.graphics.PorterDuff
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import ru.maxstelmakh.mymoney.databinding.CategoryItemLayoutBinding
import ru.maxstelmakh.mymoney.domain.model.CategoryModelDomain

class CategoriesViewHolder(
    private val categoryBinding: CategoryItemLayoutBinding
) : RecyclerView.ViewHolder(categoryBinding.root) {

    @Suppress("DEPRECATION")
    @SuppressLint("ResourceAsColor")
    fun refreshList(categoryModelDomain: CategoryModelDomain) {
        with (categoryBinding) {

            eventCategory.text = categoryModelDomain.category

            categoryCard.background
                .mutate()
                .setColorFilter(
                    Color.parseColor(categoryModelDomain.color),
                    PorterDuff.Mode.SRC_ATOP)

            root.setOnClickListener {
                Toast.makeText(it.context, "${eventCategory.text}", Toast.LENGTH_SHORT).show()
            }
        }
    }
}
