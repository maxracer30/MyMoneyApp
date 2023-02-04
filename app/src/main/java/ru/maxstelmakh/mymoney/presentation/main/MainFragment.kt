package ru.maxstelmakh.mymoney.presentation.main

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import ru.maxstelmakh.mymoney.R
import ru.maxstelmakh.mymoney.databinding.FragmentMainBinding

class MainFragment(): Fragment(R.layout.fragment_main) {

    private var _binding: FragmentMainBinding? = null
    private val binding get() = _binding

    private val viewModel by viewModels<MainViewModel>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentMainBinding.inflate(layoutInflater, container, false)
        return _binding!!.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding?.button?.setOnClickListener {
            findNavController().navigate(R.id.action_mainFragment_to_plansFragment)
        }
    }

    private fun init() {
//        val recyclerView = binding?.rvEvents
//        recyclerView?.adapter = adapter
//        viewModel.events.observe(viewLifecycleOwner) {
//            adapter.setList(it)
//        }
    }


    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}