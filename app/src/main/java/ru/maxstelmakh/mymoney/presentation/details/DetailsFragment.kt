package ru.maxstelmakh.mymoney.presentation.details

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import ru.maxstelmakh.mymoney.R
import ru.maxstelmakh.mymoney.databinding.FragmentAddNewEventBinding

@Suppress("DEPRECATION")
class DetailsFragment : Fragment() {
    private var _binding: FragmentAddNewEventBinding? = null
    private val binding get() = _binding!!

    @SuppressLint("NewApi")
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

        with (binding) {
            tvNameOfOperation.text = context?.getText(R.string.change_operation)
        }

    }


    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}
