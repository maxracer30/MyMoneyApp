package ru.maxstelmakh.mymoney.presentation.categories.addnewcategory

import android.annotation.SuppressLint
import android.graphics.Color
import android.graphics.PorterDuff
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import dagger.hilt.android.AndroidEntryPoint
import ru.maxstelmakh.mymoney.R
import ru.maxstelmakh.mymoney.databinding.FragmentAddNewCategoryBinding
import ru.maxstelmakh.mymoney.presentation.adapter.colorsadapter.ColorsAdapter
import ru.maxstelmakh.mymoney.presentation.adapter.listeners.ColorListener

@AndroidEntryPoint
class AddNewCategoryFragment : Fragment(), ColorListener {
    private var _binding: FragmentAddNewCategoryBinding? = null
    private val binding get() = _binding!!
    private val colorsAdapter = ColorsAdapter(this)
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

        setImageToImageView()

        with(binding) {

            categoryColor.adapter = colorsAdapter

            imageCategory.setOnClickListener {
                findNavController().navigate(
                    R.id.action_addNewCategoryFragment_to_iconsFragment
                )
            }



            btnEscape.setOnClickListener {
                findNavController().navigateUp()
            }

            btnSave.setOnClickListener {
                viewModel.addNewCategory(
                    icon = viewModel.selectedImage,
                    categoryColor = colors[colorsAdapter.selectedPosition],
                    name = tvEnterNameCategory.text.toString()
                )
            }
        }
    }

    fun setImageToImageView() {
        binding.imageCategory.setImageResource(viewModel.selectedImage)
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
        colorsAdapter.selectedPosition = 1

    }


    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

    @Suppress("DEPRECATION")
    override fun onColorSelected(color: String) {
        Log.d("StatesOfApp", color)
        binding.imageCategory
            .background
            .mutate()
            .setColorFilter(
                Color.parseColor(color),
                PorterDuff.Mode.SRC_ATOP
            )
    }
}