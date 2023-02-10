package ru.maxstelmakh.mymoney.presentation.addevent

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import dagger.hilt.android.AndroidEntryPoint
import ru.maxstelmakh.mymoney.databinding.FragmentAddNewEventBinding
import ru.maxstelmakh.mymoney.domain.model.EventModelDomain
import javax.inject.Inject

@AndroidEntryPoint
class AddNewEventFragment @Inject constructor(
) : DialogFragment() {


    private var _binding: FragmentAddNewEventBinding? = null
    private val binding get() = _binding
    private val viewModel by viewModels<AddNewEventViewModel>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentAddNewEventBinding.inflate(layoutInflater, container, false)
        return _binding!!.root
    }

    @SuppressLint("NewApi")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding?.apply {
            btnSave.setOnClickListener {
                viewModel.insert(
                    eventModelDomain = EventModelDomain(
                        title = title.text.toString(),
                        expense = Integer.parseInt(expense.text.toString()),
                        description = description.text.toString(),
                        category = category.text.toString()
                    )
                )
                findNavController().navigateUp()
            }
            btnCancel.setOnClickListener { findNavController().navigateUp() }
        }
    }


    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}
