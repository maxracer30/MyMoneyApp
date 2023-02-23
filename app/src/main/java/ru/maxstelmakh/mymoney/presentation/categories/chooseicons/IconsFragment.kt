package ru.maxstelmakh.mymoney.presentation.categories.chooseicons

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import ru.maxstelmakh.mymoney.R
import ru.maxstelmakh.mymoney.databinding.FragmentIconsBinding
import ru.maxstelmakh.mymoney.presentation.adapter.iconsadapter.IconsAdapter
import ru.maxstelmakh.mymoney.presentation.adapter.listeners.IconsListener

class IconsFragment : Fragment(), IconsListener {
    private var _binding: FragmentIconsBinding? = null
    private val binding get() = _binding!!
    private val iconsAdapter = IconsAdapter(this)

    private var selectedIcon = 0
    private var selectedColor = 1
    private var color = "#ff957fef"

    override fun onCreate(savedInstanceState: Bundle?) {
        arguments?.let {
            selectedColor = it.getInt("colorNumber")
            color = it.getString("color", "#ff957fef")
        }
        super.onCreate(savedInstanceState)
    }
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentIconsBinding.inflate(layoutInflater, container, false)
        return _binding!!.root
    }

    @SuppressLint("UseRequireInsteadOfGet", "DiscouragedApi")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        starting()



        with(binding) {

            iconsRecyclerView.adapter = iconsAdapter

            btnCancel.setOnClickListener {
                findNavController().navigateUp()
            }

            btnOk.setOnClickListener {
                if (selectedIcon != 0) {
                    val bundle = bundleOf("selectedIcon" to selectedIcon)
                    parentFragmentManager.setFragmentResult("iconKey", bundle)
                    findNavController().navigateUp()
                } else {
                    Toast.makeText(context, getString(R.string.icon_error), Toast.LENGTH_SHORT)
                        .show()
                }

            }
        }
    }

    @SuppressLint("DiscouragedApi", "UseRequireInsteadOfGet")
    private fun starting() {
        val icons = listOf(
            resources
                .getIdentifier("storefront", "drawable", activity!!.packageName),
            resources
                .getIdentifier("eat", "drawable", activity!!.packageName),
            resources
                .getIdentifier("home", "drawable", activity!!.packageName),
            resources
                .getIdentifier("book", "drawable", activity!!.packageName),
            resources
                .getIdentifier("car", "drawable", activity!!.packageName),
            resources
                .getIdentifier("heart", "drawable", activity!!.packageName),
            resources
                .getIdentifier("comms", "drawable", activity!!.packageName),
            resources
                .getIdentifier("fun", "drawable", activity!!.packageName),
        )

        iconsAdapter.color = color

        iconsAdapter.icons = icons
    }


    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

    override fun onIconSelected(icon: Int) {
        selectedIcon = icon
    }

}