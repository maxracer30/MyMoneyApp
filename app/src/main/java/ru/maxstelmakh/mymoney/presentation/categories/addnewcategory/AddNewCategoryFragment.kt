package ru.maxstelmakh.mymoney.presentation.categories.addnewcategory

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import ru.maxstelmakh.mymoney.R
import ru.maxstelmakh.mymoney.databinding.FragmentAddNewCategoryBinding
import ru.maxstelmakh.mymoney.presentation.adapter.colorsadapter.ColorsAdapter

class AddNewCategoryFragment : Fragment() {
    private var _binding: FragmentAddNewCategoryBinding? = null
    private val binding get() = _binding!!
    private val colorsAdapter = ColorsAdapter()
    private val viewModel by viewModels<AddNewCategoryViewModel>()

    private var colors = emptyList<String>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentAddNewCategoryBinding.inflate(layoutInflater, container, false)
        return _binding!!.root
    }

    @SuppressLint("ResourceType")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        starting()

        with(binding) {

            categoryColor.adapter = colorsAdapter

            imageCategory.setOnClickListener {
                findNavController().navigate(
                    R.id.action_addNewCategoryFragment_to_iconsFragment
                )
            }

            categoryColor.setOnClickListener {
                imageCategory.background.mutate().colorFilter

            }

            btnEscape.setOnClickListener {
                findNavController().navigateUp()
            }

            btnSave.setOnClickListener {
                viewModel.addNewCategory(
                    icon = resources.getInteger(imageCategory.id),
                    categoryColor = colors[colorsAdapter.selectedPosition],
                    name = tvEnterNameCategory.text.toString()
                )
            }
        }
    }

    private fun starting() {
        @SuppressLint("ResourceType")
        colors =
            listOf(
                resources.getString(R.color.pastel_pink),
                resources.getString(R.color.pastel_purple_blue),
                resources.getString(R.color.pastel_purple),
                resources.getString(R.color.pastel_blue),
                resources.getString(R.color.pastel_aqua_green),
                resources.getString(R.color.pastel_light_green),
                resources.getString(R.color.pastel_light_yellow),
                resources.getString(R.color.pastel_yellow),
                resources.getString(R.color.pastel_brown),
                resources.getString(R.color.pastel_orange),
                resources.getString(R.color.pastel_red),
            )
        colorsAdapter.colors = colors
    }


    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

}