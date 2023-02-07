package ru.maxstelmakh.mymoney.presentation.main

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.google.android.material.datepicker.MaterialDatePicker
import dagger.hilt.android.AndroidEntryPoint
import ru.maxstelmakh.mymoney.R
import ru.maxstelmakh.mymoney.databinding.FragmentMainBinding

@AndroidEntryPoint
class MainFragment(
) : Fragment(R.layout.fragment_main) {

    private var _binding: FragmentMainBinding? = null
    private val binding get() = _binding
    private val viewModel by viewModels<MainViewModel>()


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentMainBinding.inflate(layoutInflater, container, false)
        return _binding!!.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding?.apply {
            btnAdd.setOnClickListener {
                findNavController().navigate(R.id.action_mainFragment_to_addNewEventFragment)
            }
        }
    }


    private fun showDataRangePicker() {
        val datePicker = MaterialDatePicker.Builder
            .dateRangePicker()
            .setTitleText("Select date")
            .build()
        datePicker.show(
            parentFragmentManager.beginTransaction(),
            "date_range_picker"
        )

    }


    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}