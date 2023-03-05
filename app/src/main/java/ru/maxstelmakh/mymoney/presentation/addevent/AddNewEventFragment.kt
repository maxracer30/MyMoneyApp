package ru.maxstelmakh.mymoney.presentation.addevent

import android.graphics.PorterDuff
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.CycleInterpolator
import android.view.animation.TranslateAnimation
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

@Suppress("DEPRECATION")
@AndroidEntryPoint
class AddNewEventFragment : Fragment(), CategoryListener {

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

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        with(binding) {

            category.adapter = categoriesInAddAdapter

            viewModel.categories.observe(viewLifecycleOwner) {
                viewModel.viewModelScope.launch {
                    categoriesInAddAdapter.setList(it)
                }
            }

            btnEscape.setOnClickListener {
                findNavController().navigateUp()
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

                    tvExpense.setTextColor(resources.getColor(R.color.pastel_red))

                    val shake =
                        TranslateAnimation(
                            0f,
                            10f,
                            0f,
                            0f
                        )
                    with(shake) {
                        duration = 300
                        interpolator = CycleInterpolator(7f)
                    }

                    tvExpense.startAnimation(shake)

                    expense.startAnimation(shake)

                    expense
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
        defCategory = categoryModelDomain.categoryId
    }
}
