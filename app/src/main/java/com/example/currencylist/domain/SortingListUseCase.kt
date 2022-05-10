package com.example.currencylist.domain

import com.example.currencylist.data.CurrencyInfo
import com.example.currencylist.viewmodels.data.SortedOrder
import javax.inject.Inject

class SortingListUseCase @Inject constructor() {
    fun execute(list: List<CurrencyInfo>, currentSorted: SortedOrder): List<CurrencyInfo> {
        return when (currentSorted) {
            SortedOrder.Unsorted,
            SortedOrder.Ascending -> {
                list.sortedBy {
                    it.name
                }
            }
            SortedOrder.Descending -> {
                list.sortedByDescending {
                    it.name
                }
            }
        }
    }
}