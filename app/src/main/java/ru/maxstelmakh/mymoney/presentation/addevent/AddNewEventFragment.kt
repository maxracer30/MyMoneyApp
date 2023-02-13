package ru.maxstelmakh.mymoney.presentation.addevent

import android.graphics.PorterDuff
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import dagger.hilt.android.AndroidEntryPoint
import ru.maxstelmakh.mymoney.R
import ru.maxstelmakh.mymoney.databinding.FragmentAddNewEventBinding
import ru.maxstelmakh.mymoney.domain.model.EventModelDomain
import javax.inject.Inject

@AndroidEntryPoint
class AddNewEventFragment @Inject constructor(
) : Fragment() {


    private var _binding: FragmentAddNewEventBinding? = null
    private val binding get() = _binding!!
    private val viewModel by viewModels<AddNewEventViewModel>()

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
            btnSave.setOnClickListener {
                if (expense.text.isNotBlank() && category.text.isNotBlank()) {
                    viewModel.insert(
                        eventModelDomain = EventModelDomain(
                            expense = Integer.parseInt(expense.text.toString()),
                            description = description.text.toString().trim(),
                            category = category.text.toString().trim()
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
    }
