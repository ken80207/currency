package com.example.currencylist.domain

import com.example.currencylist.viewmodels.data.SortedOrder
import javax.inject.Inject

class SortedRuleUseCase @Inject constructor() {
    fun execute(currentSortedOrder: SortedOrder): SortedOrder {
        return when(currentSortedOrder) {
            SortedOrder.Ascending -> {
                SortedOrder.Descending
            }
            SortedOrder.Unsorted,
            SortedOrder.Descending -> {
                SortedOrder.Ascending
            }
        }
    }
}