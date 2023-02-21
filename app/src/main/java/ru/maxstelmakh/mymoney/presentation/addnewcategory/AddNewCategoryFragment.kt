package ru.maxstelmakh.mymoney.presentation.addnewcategory

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import ru.maxstelmakh.mymoney.R
import ru.maxstelmakh.mymoney.databinding.FragmentAddNewCategoryBinding

class AddNewCategoryFragment : Fragment() {
    private var _binding: FragmentAddNewCategoryBinding? = null
    private val binding get() = _binding!!
    private val viewModel by viewModels<AddNewCategoryViewModel>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentAddNewCategoryBinding.inflate(layoutInflater, container, false)
        return _binding!!.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        with(binding) {
            imageCategory.setOnClickListener {
                findNavController().navigate(
                    R.id.action_addNewCategoryFragment_to_iconsFragment)
            }
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

}