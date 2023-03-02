package ru.maxstelmakh.mymoney.presentation.categories.changecategory

import android.annotation.SuppressLint
import android.app.AlertDialog
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
class ChangeCategoryFragment : Fragment(), ColorListener, IconsListener {
    private var _binding: FragmentAddNewCategoryBinding? = null
    private val binding get() = _binding!!
    private val colorsAdapter = ColorsAdapter(this)
    private val viewModel by viewModels<ChangeCategoryViewModel>()

    private var selectedColorNumber = 1
    private var colors = emptyList<String>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            viewModel.categoryToChange = it.getParcelable("categoryModel")!!
            viewModel.changedColor = viewModel.categoryToChange.color
        }
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

        with(binding) {

            btnDeleteCategory.visibility = View.VISIBLE

            with(viewModel) {
                selectedImage = categoryToChange.image
                changedName = categoryToChange.categoryName
            }

            setImageToImageView()
            starting()
            getColorNumber()

            tvNameOfCatOperation.text = getString(R.string.change_category)
            tvColor.text = getString(R.string.color)
            tvSetIcon.text = getString(R.string.icon)
            editNameCategory.setText(viewModel.changedName)

            categoryColor.adapter = colorsAdapter

            imageCategory.setOnClickListener {
                val bundle = bundleOf(
                    "colorPosition" to selectedColorNumber,
                    "color" to colorsAdapter.colors[selectedColorNumber]
                )
                findNavController().navigate(
                    R.id.action_changeCategoryFragment_to_iconsFragment,
                    bundle
                )
            }

            btnDeleteCategory.setOnClickListener {

                AlertDialog.Builder(context)
                    .setTitle(getString(R.string.a_u_sure))
                    .setMessage(getString(R.string.a_u_sure_message))
                    .setNegativeButton(getString(R.string.not_sure)) { dialog, _ ->
                        dialog.cancel()
                    }
                    .setPositiveButton(getString(R.string.sure)) { dialog, _ ->
                        viewModel.deleteCategory()
                        findNavController().navigateUp()
                        dialog.cancel()
                    }
                    .create()
                    .show()
            }

            btnEscape.setOnClickListener {
                findNavController().navigateUp()
            }

            btnSave.setOnClickListener {
                if (editNameCategory.text.isNotBlank()) {
                    viewModel.changedName = editNameCategory.text.trim().toString()
                    viewModel.changeCategory()
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

    private fun setImageToImageView() {
        binding.imageCategory.setImageResource(viewModel.selectedImage)
        binding.imageCategory
            .background
            .mutate()
            .setColorFilter(
                Color.parseColor(viewModel.changedColor),
                PorterDuff.Mode.SRC_ATOP
            )
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

    private fun getColorNumber() {


        selectedColorNumber = colors.indexOf(viewModel.changedColor)
        binding.imageCategory
            .background
            .mutate()
            .setColorFilter(
                Color.parseColor(colors[selectedColorNumber]),
                PorterDuff.Mode.SRC_ATOP
            )

        colorsAdapter.selectedPosition = selectedColorNumber
    }


    @Suppress("DEPRECATION")
    override fun onColorSelected(color: String, colorNumber: Int) {
        selectedColorNumber = colorNumber
        viewModel.changedColor = colors[selectedColorNumber]
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
