package ru.maxstelmakh.mymoney.presentation.adapter.categoriesinaddadapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import ru.maxstelmakh.mymoney.databinding.CategoryHorizontalItemLayoutBinding
import ru.maxstelmakh.mymoney.domain.model.CategoryModelDomain
import ru.maxstelmakh.mymoney.presentation.adapter.listeners.CategoryListener

class CategoriesInAddAdapter(
    private val listener: CategoryListener,
) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private var categoriesList = emptyList<CategoryModelDomain>()

    private var selectedPosition = -1
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val view =
            CategoryHorizontalItemLayoutBinding.inflate(
                LayoutInflater
                    .from(parent.context), parent, false
            )
        return CategoryInAddViewHolder(view)
    }

    override fun getItemCount() = categoriesList.size

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        (holder as CategoryInAddViewHolder).refreshList(categoriesList[position])

        if (selectedPosition == position) {
            holder.itemView.isSelected = true //using selector drawable
            holder.setCheck()
        } else {
            holder.itemView.isSelected = false
            holder.setUnCheck()
        }

        holder.itemView.setOnClickListener { v ->
            if (selectedPosition >= 0) notifyItemChanged(selectedPosition)
            selectedPosition = holder.bindingAdapterPosition
            listener.onClick(categoriesList[position])
            notifyItemChanged(selectedPosition)
        }
    }

    @SuppressLint("NotifyDataSetChanged")
    fun setList(it: List<CategoryModelDomain>) {
        categoriesList = it
        notifyDataSetChanged()
    }

}
