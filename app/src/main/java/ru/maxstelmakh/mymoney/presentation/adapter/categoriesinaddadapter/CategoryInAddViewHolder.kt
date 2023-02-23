package ru.maxstelmakh.mymoney.presentation.adapter.categoriesinaddadapter

import android.annotation.SuppressLint
import android.graphics.Color
import android.graphics.PorterDuff
import android.view.View
import androidx.recyclerview.widget.RecyclerView
import ru.maxstelmakh.mymoney.databinding.CategoryHorizontalItemLayoutBinding
import ru.maxstelmakh.mymoney.domain.model.CategoryModelDomain

class CategoryInAddViewHolder(
    private val categoryBinding: CategoryHorizontalItemLayoutBinding
) : RecyclerView.ViewHolder(categoryBinding.root) {

    @Suppress("DEPRECATION")
    @SuppressLint("ResourceAsColor")
    fun refreshList(categoryModelDomain: CategoryModelDomain) {
        with (categoryBinding) {



            eventCategory.text = categoryModelDomain.categoryName

            categoryCard.background
                .mutate()
                .setColorFilter(
                    Color.parseColor(categoryModelDomain.color),
                    PorterDuff.Mode.SRC_ATOP)

            imageCategory.setImageResource(categoryModelDomain.image)

        }
    }

    fun setCheck() {
        categoryBinding.checkerCategory.visibility = View.VISIBLE
    }

    fun setUnCheck() {
        categoryBinding.checkerCategory.visibility = View.GONE
    }
}
