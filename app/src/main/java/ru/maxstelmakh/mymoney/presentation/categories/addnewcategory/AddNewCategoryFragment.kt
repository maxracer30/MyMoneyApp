package ru.maxstelmakh.mymoney.presentation.categories.addnewcategory

import android.annotation.SuppressLint
import android.graphics.Color
import android.graphics.PorterDuff
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.CycleInterpolator
import android.view.animation.TranslateAnimation
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.fragment.app.setFragmentResultListener
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import dagger.hilt.android.AndroidEntryPoint
import ru.maxstelmakh.mymoney.R
import ru.maxstelmakh.mymoney.databinding.FragmentAddNewCategoryBinding
import ru.maxstelmakh.mymoney.presentation.adapter.colorsadapter.ColorsAdapter
import ru.maxstelmakh.mymoney.presentation.adapter.listeners.ColorListener
import ru.maxstelmakh.mymoney.presentation.adapter.listeners.IconsListener

@Suppress("DEPRECATION")
@AndroidEntryPoint
class AddNewCategoryFragment : Fragment(), ColorListener, IconsListener {
    private var _binding: FragmentAddNewCategoryBinding? = null
    private val binding get() = _binding!!
    private val colorsAdapter = ColorsAdapter(this)
    private val viewModel by viewModels<AddNewCategoryViewModel>()

    private var selectedColorNumber = 1
    private var colors = emptyList<String>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setFragmentResultListener("iconKey") { _, bundle ->
            viewModel.selectedImage = bundle.getInt("selectedIcon")
        }
    }

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
                val bundle = bundleOf(
                    "colorPosition" to selectedColorNumber,
                    "color" to colorsAdapter.colors[selectedColorNumber]
                )
                findNavController().navigate(
                    R.id.action_addNewCategoryFragment_to_iconsFragment,
                    bundle
                )
            }



            btnEscape.setOnClickListener {
                findNavController().navigateUp()
            }

            btnSave.setOnClickListener {
                if (editNameCategory.text.isNotBlank()) {
                    viewModel.addNewCategory(
                        icon = viewModel.selectedImage,
                        categoryColor = colors[colorsAdapter.selectedPosition],
                        name = editNameCategory.text.toString()
                    )
                    findNavController().navigateUp()
                } else {
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


                    editNameCategory.startAnimation(shake)
                    tvEnterName.startAnimation(shake)

                    tvEnterName.setTextColor(resources.getColor(R.color.pastel_red))

                    editNameCategory
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

    override fun onResume() {
        super.onResume()
        setImageToImageView()
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
        colorsAdapter.selectedPosition = selectedColorNumber

    }


    @Suppress("DEPRECATION")
    override fun onColorSelected(color: String, colorNumber: Int) {
        selectedColorNumber = colorNumber
        binding.imageCategory
            .background
            .mutate()
            .setColorFilter(
                Color.parseColor(color),
                PorterDuff.Mode.SRC_ATOP
            )
    }

    override fun onIconSelected(icon: Int) {
        viewModel.selectedImage = icon
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}
