package ru.maxstelmakh.mymoney.presentation.addevent

import android.graphics.PorterDuff
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.viewModelScope
import androidx.navigation.fragment.findNavController
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch
import ru.maxstelmakh.mymoney.R
import ru.maxstelmakh.mymoney.databinding.FragmentAddNewEventBinding
import ru.maxstelmakh.mymoney.domain.model.CategoryModelDomain
import ru.maxstelmakh.mymoney.domain.model.EventModelDomain
import ru.maxstelmakh.mymoney.presentation.adapter.categoriesinaddadapter.CategoriesInAddAdapter
import ru.maxstelmakh.mymoney.presentation.adapter.listeners.CategoryListener
import javax.inject.Inject

@AndroidEntryPoint
class AddNewEventFragment @Inject constructor(
) : Fragment(), CategoryListener {


    private var _binding: FragmentAddNewEventBinding? = null
    private val binding get() = _binding!!
    private val viewModel by viewModels<AddNewEventViewModel>()
    private var defCategory: Int = 1
    private val categoriesInAddAdapter = CategoriesInAddAdapter(this)

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentAddNewEventBinding.inflate(layoutInflater, container, false)
        return _binding!!.root
    }

    @Suppress("DEPRECATION")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        with(binding) {

            category.adapter = categoriesInAddAdapter

            viewModel.categories.observe(viewLifecycleOwner) {
                viewModel.viewModelScope.launch {
                    categoriesInAddAdapter.setList(it)
                }
            }

            btnSave.setOnClickListener {
                if (expense.text.isNotBlank()) {
                    viewModel.insert(
                        eventModelDomain = EventModelDomain(
                            expense = expense.text.toString().toLong(),
                            description = description.text.toString().trim(),
                            category = defCategory
                        )
                    )
                    findNavController().navigateUp()
                } else {
                    expense
                        .background
                        .mutate()
                        .setColorFilter(
                            resources.getColor(R.color.orangered),
                            PorterDuff.Mode.SRC_ATOP
                        )

                    category
                        .background
                        .mutate()
                        .setColorFilter(
                            resources.getColor(R.color.orangered),
                            PorterDuff.Mode.SRC_ATOP
                        )
                }
            }
        }
    }


    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

    override fun onClick(categoryModelDomain: CategoryModelDomain) {
        categoriesInAddAdapter
        defCategory = categoryModelDomain.categoryId
    }
}
