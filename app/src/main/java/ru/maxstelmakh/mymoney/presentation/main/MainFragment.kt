package ru.maxstelmakh.mymoney.presentation.main

import android.annotation.SuppressLint
import android.graphics.Color
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.viewModelScope
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.datepicker.MaterialDatePicker
import com.google.android.material.snackbar.BaseTransientBottomBar
import com.google.android.material.snackbar.Snackbar
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch
import ru.maxstelmakh.mymoney.R
import ru.maxstelmakh.mymoney.databinding.FragmentMainBinding
import ru.maxstelmakh.mymoney.presentation.adapter.eventsadapter.EventsAdapter
import ru.maxstelmakh.mymoney.presentation.adapter.swipegesture.SwipeGesture

@AndroidEntryPoint
class MainFragment : Fragment(R.layout.fragment_main) {

    private var _binding: FragmentMainBinding? = null
    private val binding get() = _binding!!
    private val viewModel by viewModels<MainViewModel>()
    private val eventsAdapter = EventsAdapter()


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentMainBinding.inflate(layoutInflater, container, false)
        return _binding!!.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        init()
    }

    private fun init() = with(binding) {

        eventsRecyclerView.adapter = eventsAdapter

        btnAdd.setOnClickListener {
            findNavController().navigate(R.id.action_mainFragment_to_addNewEventFragment)
        }

        viewModel.events.observe(viewLifecycleOwner) {
            viewModel.viewModelScope.launch {
                eventsAdapter.setList(it)
            }
        }

        swipeToGesture()
    }

    private fun swipeToGesture() {
        val swipeGesture = object : SwipeGesture(context = binding.root.context) {
            @SuppressLint("ResourceType", "ShowToast")
            override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
                val position = viewHolder.bindingAdapterPosition
                try {

                    when (direction) {
                        ItemTouchHelper.LEFT -> {
                            val eventModelDomain = eventsAdapter.oldEventsList[position]

                            viewModel.deleteEvent(eventModelDomain)

                            Snackbar.make(
                                activity!!.findViewById(R.id.activity_main),
                                getString(R.string.item_deleted),
                                Snackbar.LENGTH_SHORT
                            ).addCallback(object : BaseTransientBottomBar.BaseCallback<Snackbar>() {
                                override fun onDismissed(
                                    transientBottomBar: Snackbar?,
                                    event: Int
                                ) {
                                    super.onDismissed(transientBottomBar, event)
                                }

                                override fun onShown(transientBottomBar: Snackbar?) {

                                    transientBottomBar?.setAction(getString(R.string.undo)) {
                                        viewModel.addEvent(eventModelDomain)
                                    }
                                    super.onShown(transientBottomBar)
                                }
                            }).apply {
                                animationMode = Snackbar.ANIMATION_MODE_SLIDE
                            }
                                .setActionTextColor(Color.RED)
                                .show()
                        }

                        ItemTouchHelper.RIGHT -> {
                            findNavController().navigate(R.id.action_mainFragment_to_detailsFragment)
                        }
                    }
                } catch (e: Exception) {
                    Toast.makeText(this@MainFragment.context, "${e.message}", Toast.LENGTH_SHORT)
                        .show()
                }
            }
        }

        val touchHelper = ItemTouchHelper(swipeGesture)

        touchHelper.attachToRecyclerView(binding.eventsRecyclerView)
    }

    private fun showDataRangePicker() {
        val datePicker = MaterialDatePicker.Builder
            .dateRangePicker()
            .setTitleText("Select date")
            .build()
        datePicker.show(
            parentFragmentManager.beginTransaction(),
            "date_range_picker"
        )

    }


    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}