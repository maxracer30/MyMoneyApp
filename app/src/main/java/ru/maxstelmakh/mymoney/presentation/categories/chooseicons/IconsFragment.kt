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
//            resources
//                .getIdentifier("storefront", "drawable", activity!!.packageName),
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
            resources
                .getIdentifier("bank", "drawable", activity!!.packageName),
            resources
                .getIdentifier("airplane", "drawable", activity!!.packageName),
            resources
                .getIdentifier("pets", "drawable", activity!!.packageName),
            resources
                .getIdentifier("shuttle", "drawable", activity!!.packageName),
            resources
                .getIdentifier("apartment", "drawable", activity!!.packageName),
            resources
                .getIdentifier("money", "drawable", activity!!.packageName),
            resources
                .getIdentifier("attractions", "drawable", activity!!.packageName),
            resources
                .getIdentifier("audio", "drawable", activity!!.packageName),
            resources
                .getIdentifier("croissant", "drawable", activity!!.packageName),
            resources
                .getIdentifier("beach", "drawable", activity!!.packageName),
            resources
                .getIdentifier("horse", "drawable", activity!!.packageName),
            resources
                .getIdentifier("bike", "drawable", activity!!.packageName),
            resources
                .getIdentifier("blind", "drawable", activity!!.packageName),
            resources
                .getIdentifier("brush", "drawable", activity!!.packageName),
            resources
                .getIdentifier("business", "drawable", activity!!.packageName),
            resources
                .getIdentifier("repair", "drawable", activity!!.packageName),
            resources
                .getIdentifier("camera", "drawable", activity!!.packageName),
            resources
                .getIdentifier("carpenter", "drawable", activity!!.packageName),
            resources
                .getIdentifier("celebration", "drawable", activity!!.packageName),
            resources
                .getIdentifier("chair", "drawable", activity!!.packageName),
            resources
                .getIdentifier("child", "drawable", activity!!.packageName),
            resources
                .getIdentifier("church", "drawable", activity!!.packageName),
            resources
                .getIdentifier("cloud", "drawable", activity!!.packageName),
            resources
                .getIdentifier("coffee", "drawable", activity!!.packageName),
            resources
                .getIdentifier("palette", "drawable", activity!!.packageName),
            resources
                .getIdentifier("computer", "drawable", activity!!.packageName),
            resources
                .getIdentifier("construction", "drawable", activity!!.packageName),
            resources
                .getIdentifier("cottage", "drawable", activity!!.packageName),
            resources
                .getIdentifier("rocket", "drawable", activity!!.packageName),
            resources
                .getIdentifier("crop", "drawable", activity!!.packageName),
            resources
                .getIdentifier("bunny", "drawable", activity!!.packageName),
            resources
                .getIdentifier("bitcoin", "drawable", activity!!.packageName),
            resources
                .getIdentifier("delivery", "drawable", activity!!.packageName),
            resources
                .getIdentifier("diamond", "drawable", activity!!.packageName),
            resources
                .getIdentifier("directions_bike", "drawable", activity!!.packageName),
            resources
                .getIdentifier("boat", "drawable", activity!!.packageName),
            resources
                .getIdentifier("sports", "drawable", activity!!.packageName),
            resources
                .getIdentifier("bus", "drawable", activity!!.packageName),
            resources
                .getIdentifier("train", "drawable", activity!!.packageName),
            resources
                .getIdentifier("skiing", "drawable", activity!!.packageName),
            resources
                .getIdentifier("dry", "drawable", activity!!.packageName),
            resources
                .getIdentifier("elderly", "drawable", activity!!.packageName),
            resources
                .getIdentifier("nature", "drawable", activity!!.packageName),
            resources
                .getIdentifier("objects", "drawable", activity!!.packageName),
            resources
                .getIdentifier("forest", "drawable", activity!!.packageName),
            resources
                .getIdentifier("paint", "drawable", activity!!.packageName),
            resources
                .getIdentifier("ice_skating", "drawable", activity!!.packageName),
            resources
                .getIdentifier("kayaking", "drawable", activity!!.packageName),
            resources
                .getIdentifier("liquor", "drawable", activity!!.packageName),
            resources
                .getIdentifier("gas_station", "drawable", activity!!.packageName),
            resources
                .getIdentifier("burger", "drawable", activity!!.packageName),
            resources
                .getIdentifier("mouse", "drawable", activity!!.packageName),
            resources
                .getIdentifier("sell", "drawable", activity!!.packageName),
            resources
                .getIdentifier("shopping_basket", "drawable", activity!!.packageName),
            resources
                .getIdentifier("smoking", "drawable", activity!!.packageName),
            resources
                .getIdentifier("hockey", "drawable", activity!!.packageName),
            resources
                .getIdentifier("sports_kabaddi", "drawable", activity!!.packageName),
            resources
                .getIdentifier("motorsports", "drawable", activity!!.packageName),
            resources
                .getIdentifier("soccer", "drawable", activity!!.packageName),
            resources
                .getIdentifier("store", "drawable", activity!!.packageName),
            resources
                .getIdentifier("theater", "drawable", activity!!.packageName),
            resources
                .getIdentifier("two_wheeler", "drawable", activity!!.packageName),
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