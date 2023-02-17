package ru.maxstelmakh.mymoney.presentation.details

import android.annotation.SuppressLint
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

@Suppress("DEPRECATION")
@SuppressLint("NewApi")
@AndroidEntryPoint
class DetailsFragment : Fragment() {
    private var _binding: FragmentAddNewEventBinding? = null
    private val binding get() = _binding!!

    private val viewModel by viewModels<DetailsViewModel>()
    private lateinit var eventToChange: EventModelDomain

    override fun onCreate(savedInstanceState: Bundle?) {
        arguments?.let { bundle ->
            eventToChange = bundle.getParcelable("eventToChange")!!
        }
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentAddNewEventBinding.inflate(layoutInflater, container, false)

        return _binding!!.root
    }

    @SuppressLint("SetTextI18n")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)



        with(binding) {

            tvNameOfOperation.text = resources.getString(R.string.change_operation)
            expense.setText(eventToChange.expense.toString() + "")
            expense.setSelectAllOnFocus(true)
//            category.setText(eventToChange.category)
            description.setText(eventToChange.description)

            btnSave.setOnClickListener {
                if (expense.text.isNotBlank()) {
                    viewModel.update(
                        eventModelDomain = EventModelDomain(
                            id = eventToChange.id,
                            expense = Integer.parseInt(expense.text.toString()),
                            description = description.text.toString().trim(),
//                            category = "default",
                            joined_date = eventToChange.joined_date
                        )
                    )
                    findNavController().navigateUp()
                } else {
                    expense
                        .background
                        .mutate()
                        .setColorFilter(
                            resources.getColor(R.color.orangered),
                            PorterDuff.Mode.SRC_ATOP)

                    category
                        .background
                        .mutate()
                        .setColorFilter(
                            resources.getColor(R.color.orangered),
                            PorterDuff.Mode.SRC_ATOP)

                }
            }
        }
    }


    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}
