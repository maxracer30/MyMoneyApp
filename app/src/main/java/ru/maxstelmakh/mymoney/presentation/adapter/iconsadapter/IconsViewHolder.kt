package ru.maxstelmakh.mymoney.presentation.adapter.iconsadapter

import android.annotation.SuppressLint
import androidx.recyclerview.widget.RecyclerView
import ru.maxstelmakh.mymoney.databinding.IconItemLayoutBinding

class IconsViewHolder(
    private val binding: IconItemLayoutBinding
) : RecyclerView.ViewHolder(binding.root) {

    fun showIcons(icon: Int) {

        with(binding) {
            imageView1.setImageResource(icon)
        }
    }

    @SuppressLint("ResourceAsColor")
    fun setCheck(color: String) {
        binding.view
            .background
            .mutate()
            .setColorFilter(
                android.graphics.Color.parseColor(color),
                android.graphics.PorterDuff.Mode.SRC_ATOP
            )
    }

    @SuppressLint("ResourceAsColor")
    fun setUnCheck() {
        binding.view
            .background
            .mutate()
            .setColorFilter(
                android.graphics.Color.LTGRAY,
                android.graphics.PorterDuff.Mode.SRC_ATOP
            )
    }

}
