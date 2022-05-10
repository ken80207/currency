package com.example.currencylist.viewmodels.data

sealed class SortedOrder{
    object Unsorted: SortedOrder()
    object Descending: SortedOrder()
    object Ascending: SortedOrder()
}
