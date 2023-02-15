package ru.maxstelmakh.mymoney.presentation.adapter.listeners

import ru.maxstelmakh.mymoney.domain.model.CategoryModelDomain

interface CategoryListener {
    fun onClick(categoryModelDomain: CategoryModelDomain)
}