package ru.maxstelmakh.mymoney.presentation.main

import android.annotation.SuppressLint
import android.graphics.Color
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.viewModelScope
import androidx.navigation.fragment.findNavController
import androidx.preference.PreferenceManager
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.datepicker.MaterialDatePicker
import com.google.android.material.snackbar.BaseTransientBottomBar
import com.google.android.material.snackbar.Snackbar
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import ru.maxstelmakh.mymoney.R
import ru.maxstelmakh.mymoney.databinding.FragmentMainBinding
import ru.maxstelmakh.mymoney.domain.model.EventInDetailModelDomain
import ru.maxstelmakh.mymoney.domain.model.EventModelDomain
import ru.maxstelmakh.mymoney.presentation.adapter.eventsadapter.EventsAdapter
import ru.maxstelmakh.mymoney.presentation.adapter.listeners.EventsListener
import ru.maxstelmakh.mymoney.presentation.adapter.swipegesture.SwipeGesture

@AndroidEntryPoint
class MainFragment : Fragment(R.layout.fragment_main), EventsListener {

    private var _binding: FragmentMainBinding? = null
    private val binding get() = _binding!!
    private val viewModel by viewModels<MainViewModel>()
    private val eventsAdapter = EventsAdapter(this)


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentMainBinding.inflate(layoutInflater, container, false)
        return _binding!!.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        firstStart()

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
                val eventInDetailsModelDomain = eventsAdapter.oldEventsList[position]
                val eventModelDomain = EventModelDomain(
                    id = eventInDetailsModelDomain.eventId,
                    expense = eventInDetailsModelDomain.expense,
                    description = eventInDetailsModelDomain.description,
                    category = eventInDetailsModelDomain.categoryId,
                    joined_date = eventInDetailsModelDomain.joined_date
                )
                try {

                    when (direction) {
                        ItemTouchHelper.LEFT -> {
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
                            onClick(eventInDetailsModelDomain)
                        }
                    }
                } catch (e: Exception) {
                    Toast.makeText(context, "${e.message}", Toast.LENGTH_SHORT)
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

    @SuppressLint("UseRequireInsteadOfGet", "ResourceType")
    private fun firstStart() {


        val getPrefs = PreferenceManager.getDefaultSharedPreferences(activity!!)
        val editor = getPrefs.edit()
        val isFirstStart = getPrefs.getBoolean("FIRST_START", true)

        if (isFirstStart) {
            val colors =
                listOf(
                    resources.getString(R.color.pastel_yellow),
                    resources.getString(R.color.pastel_aqua_green),
                    resources.getString(R.color.pastel_brown),
                    resources.getString(R.color.pastel_pink),
                    resources.getString(R.color.pastel_orange),
                    resources.getString(R.color.pastel_red),
                    resources.getString(R.color.pastel_blue),
                    resources.getString(R.color.pastel_light_green),
                )
            val titles =
                listOf(
                    getString(R.string.other),
                    resources.getString(R.string.Food),
                    resources.getString(R.string.House),
                    resources.getString(R.string.Education),
                    resources.getString(R.string.Car),
                    resources.getString(R.string.Health),
                    resources.getString(R.string.Sport),
                    resources.getString(R.string.Fun)
                )

            val images =
                listOf(
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

            viewModel.viewModelScope.launch(Dispatchers.IO) {
                viewModel.firstStart(titles, colors, images)
            }
            editor
                .putBoolean("FIRST_START", false)
                .apply()
        }

    }


    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override fun onClick(eventModelDomain: EventInDetailModelDomain) {
        val bundle = bundleOf("eventToChange" to eventModelDomain)
        findNavController().navigate(
            R.id.action_mainFragment_to_detailsFragment,
            bundle
        )
    }
}