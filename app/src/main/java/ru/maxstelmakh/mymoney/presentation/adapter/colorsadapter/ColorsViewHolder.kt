package ru.maxstelmakh.mymoney.presentation.adapter.colorsadapter

import android.graphics.Color
import android.graphics.PorterDuff
import android.view.View
import androidx.recyclerview.widget.RecyclerView
import ru.maxstelmakh.mymoney.databinding.ColorsHorizontalItemLayoutBinding

class ColorsViewHolder(
    private val layoutBinding: ColorsHorizontalItemLayoutBinding,
) : RecyclerView.ViewHolder(layoutBinding.root) {
    @Suppress("DEPRECATION")
    fun showColors(color: String) {

        with(layoutBinding) {

            rounder.background
                .mutate()
                .setColorFilter(
                    Color.parseColor(color),
                    PorterDuff.Mode.SRC_ATOP
                )
        }
    }

    fun setCheck() {
        layoutBinding.checker.visibility = View.VISIBLE
    }

    fun setUnCheck() {
        layoutBinding.checker.visibility = View.GONE
    }
}
