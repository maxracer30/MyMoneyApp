package ru.maxstelmakh.mymoney.presentation.main

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import ru.maxstelmakh.mymoney.R
import ru.maxstelmakh.mymoney.domain.usecases.GetAllEventsUseCase
import javax.inject.Inject

class MainFragment @Inject constructor(
    private val getAllEventsUseCase: GetAllEventsUseCase
) : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_main, container, false)
        getAllEventsUseCase
    }
}