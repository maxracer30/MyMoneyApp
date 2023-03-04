package ru.maxstelmakh.mymoney.presentation.adapter.colorsadapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import ru.maxstelmakh.mymoney.databinding.ColorsHorizontalItemLayoutBinding
import ru.maxstelmakh.mymoney.presentation.adapter.listeners.ColorListener


class ColorsAdapter(
    private val listener: ColorListener
) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    var colors = emptyList<String>()

    var selectedPosition = -1

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val view = ColorsHorizontalItemLayoutBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )

        return ColorsViewHolder(view)
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        (holder as ColorsViewHolder).showColors(colors[position])

        if (selectedPosition == position) {
            holder.itemView.isSelected = true //using selector drawable
            holder.setCheck()
            listener.onColorSelected(colors[position], position)
        } else {
            holder.itemView.isSelected = false
            holder.setUnCheck()
        }

        holder.itemView.setOnClickListener {
            if (selectedPosition >= 0) notifyItemChanged(selectedPosition)
            selectedPosition = holder.bindingAdapterPosition
            notifyItemChanged(selectedPosition)
        }
    }

    override fun getItemCount() = colors.size
}