package ru.maxstelmakh.mymoney.presentation.adapter.categoriesinaddadapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import ru.maxstelmakh.mymoney.databinding.CategoryHorizontalItemLayoutBinding
import ru.maxstelmakh.mymoney.domain.model.CategoryModelDomain
import ru.maxstelmakh.mymoney.presentation.adapter.listeners.CategoryListener

class CategoriesInAddAdapter(
    private val listener: CategoryListener,
) : ListAdapter<CategoryModelDomain, RecyclerView.ViewHolder>(DiffCallback()) {

    private var selectedPosition = -1
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val view =
            CategoryHorizontalItemLayoutBinding.inflate(
                LayoutInflater
                    .from(parent.context), parent, false
            )
        return CategoryInAddViewHolder(view)
    }

    override fun getItemCount() = currentList.size

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        (holder as CategoryInAddViewHolder).refreshList(currentList[position])

        if (selectedPosition == position) {
            holder.itemView.isSelected = true //using selector drawable
            holder.setCheck()
        } else {
            holder.itemView.isSelected = false
            holder.setUnCheck()
        }

        holder.itemView.setOnClickListener {
            if (selectedPosition >= 0)
                selectedPosition = holder.bindingAdapterPosition
            listener.onClick(currentList[position])
        }
    }

    class DiffCallback : DiffUtil.ItemCallback<CategoryModelDomain>() {
        override fun areItemsTheSame(
            oldItem: CategoryModelDomain, newItem: CategoryModelDomain
        ) = oldItem.categoryId == newItem.categoryId


        override fun areContentsTheSame(
            oldItem: CategoryModelDomain,
            newItem: CategoryModelDomain
        ) = oldItem == newItem
    }

}
