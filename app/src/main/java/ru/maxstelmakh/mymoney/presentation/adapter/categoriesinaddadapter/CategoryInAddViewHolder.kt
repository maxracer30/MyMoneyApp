package ru.maxstelmakh.mymoney.presentation.adapter.categoriesinaddadapter

import android.annotation.SuppressLint
import android.graphics.Color
import android.graphics.PorterDuff
import androidx.recyclerview.widget.RecyclerView
import ru.maxstelmakh.mymoney.databinding.CategoryHorizontalItemLayoutBinding
import ru.maxstelmakh.mymoney.domain.model.CategoryModelDomain
import ru.maxstelmakh.mymoney.presentation.adapter.listeners.CategoryListener

class CategoryInAddViewHolder(
    private val categoryBinding: CategoryHorizontalItemLayoutBinding
) : RecyclerView.ViewHolder(categoryBinding.root) {

    @Suppress("DEPRECATION")
    @SuppressLint("ResourceAsColor")
    fun refreshList(categoryModelDomain: CategoryModelDomain, listener: CategoryListener) {
        with (categoryBinding) {



            eventCategory.text = categoryModelDomain.category

            categoryCard.background
                .mutate()
                .setColorFilter(
                    Color.parseColor(categoryModelDomain.color),
                    PorterDuff.Mode.SRC_ATOP)

            imageCategory.setImageResource(categoryModelDomain.image)

            itemView.setOnClickListener {
                listener.onClick(categoryModelDomain)
                categoryCard.background
                    .mutate()
                    .setColorFilter(
                        Color.RED,
                        PorterDuff.Mode.SRC_ATOP)
            }
        }
    }
}
