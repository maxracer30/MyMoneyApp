package ru.maxstelmakh.mymoney.presentation.categories

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.viewModelScope
import androidx.navigation.fragment.findNavController
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch
import ru.maxstelmakh.mymoney.R
import ru.maxstelmakh.mymoney.databinding.FragmentCategoriesBinding
import ru.maxstelmakh.mymoney.domain.model.CategoryModelDomain
import ru.maxstelmakh.mymoney.presentation.adapter.categoriesadapter.CategoriesAdapter
import ru.maxstelmakh.mymoney.presentation.adapter.listeners.AddCategoryListener
import ru.maxstelmakh.mymoney.presentation.adapter.listeners.CategoryListener

@AndroidEntryPoint
class CategoriesFragment : Fragment(R.layout.fragment_categories), CategoryListener,
    AddCategoryListener {
    private var _binding: FragmentCategoriesBinding? = null
    private val binding get() = _binding!!
    private val viewModel by viewModels<CategoryViewModel>()
    private val categoriesAdapter = CategoriesAdapter(this, this)


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentCategoriesBinding.inflate(layoutInflater, container, false)
        return _binding!!.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        init()
    }

    private fun init() = with(binding) {


        categoriesRecyclerView.adapter = categoriesAdapter


        viewModel.categories.observe(viewLifecycleOwner) {
            viewModel.viewModelScope.launch {
                categoriesAdapter.setList(it)
            }
        }
    }


    override fun onClick(categoryModelDomain: CategoryModelDomain) {
//        val bundle = bundleOf("categoryModel" to categoryModelDomain)
//        findNavController().navigate(
//            R.id.action_categoriesFragment_to_addNewCategoryFragment,
//            bundle
//        )
    }

    override fun onAddClick() {
        findNavController().navigate(
            R.id.action_categoriesFragment_to_addNewCategoryFragment
        )
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

}