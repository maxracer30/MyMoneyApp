package ru.maxstelmakh.mymoney.presentation.addevent

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import ru.maxstelmakh.mymoney.databinding.FragmentAddNewEventBinding

class AddNewEventFragment : Fragment() {

    private var _binding: FragmentAddNewEventBinding? = null
    private val binding get() = _binding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentAddNewEventBinding.inflate(layoutInflater, container, false)
        return _binding!!.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


    }
















    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}
