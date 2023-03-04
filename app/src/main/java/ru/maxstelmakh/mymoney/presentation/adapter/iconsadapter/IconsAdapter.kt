package ru.maxstelmakh.mymoney.presentation.adapter.iconsadapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import ru.maxstelmakh.mymoney.databinding.IconItemLayoutBinding
import ru.maxstelmakh.mymoney.presentation.adapter.listeners.IconsListener

class IconsAdapter(
    private val listener: IconsListener
) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    var icons = emptyList<Int>()

    var selectedPosition = -1
    var color = "#ff957fef"

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val view = IconItemLayoutBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )

        return IconsViewHolder(view)
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        (holder as IconsViewHolder).showIcons(icons[position])

        if (selectedPosition == position) {
            holder.itemView.isSelected = true //using selector drawable
            holder.setCheck(color)
            listener.onIconSelected(icons[position])
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

    override fun getItemCount() = icons.size

}