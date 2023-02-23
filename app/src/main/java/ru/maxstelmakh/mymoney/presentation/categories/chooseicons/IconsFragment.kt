package ru.maxstelmakh.mymoney.presentation.categories.chooseicons

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import ru.maxstelmakh.mymoney.databinding.FragmentIconsBinding

class IconsFragment : Fragment() {
    private var _binding: FragmentIconsBinding? = null
    private val binding get() = _binding!!

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

        val icons = listOf(
            resources
                .getIdentifier("airplane", "drawable", activity!!.packageName),
            resources
                .getIdentifier("audio", "drawable", activity!!.packageName),
            resources
                .getIdentifier("baby", "drawable", activity!!.packageName),
            resources
                .getIdentifier("basketball", "drawable", activity!!.packageName)
        )


        with(binding) {

//            iconsRecyclerView.adapter =

            btnCancel.setOnClickListener {
                findNavController().navigateUp()
            }

            btnOk.setOnClickListener {

                findNavController().navigateUp()
            }
        }
    }


    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

}