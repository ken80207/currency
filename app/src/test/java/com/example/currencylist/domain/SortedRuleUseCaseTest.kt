package com.example.currencylist.domain

import com.example.currencylist.viewmodels.data.SortedOrder
import org.junit.Assert.*

import org.junit.Before
import org.junit.Test

class SortedRuleUseCaseTest {
    lateinit var sortedRuleUseCase: SortedRuleUseCase

    @Before
    fun setUp() {
        sortedRuleUseCase = SortedRuleUseCase()
    }

    @Test
    fun execute_unsorted_order() {
        val nextSortedOrder = sortedRuleUseCase.execute(SortedOrder.Unsorted)
        assertEquals(nextSortedOrder, SortedOrder.Ascending)
    }

    @Test
    fun execute_descending_order() {
        val nextSortedOrder = sortedRuleUseCase.execute(SortedOrder.Descending)
        assertEquals(nextSortedOrder, SortedOrder.Ascending)
    }

    @Test
    fun execute_ascending_order() {
        val nextSortedOrder = sortedRuleUseCase.execute(SortedOrder.Ascending)
        assertEquals(nextSortedOrder, SortedOrder.Descending)
    }
}