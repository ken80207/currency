package com.example.currencylist.domain

import com.example.currencylist.TestUtil
import com.example.currencylist.viewmodels.data.SortedOrder
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test

class SortingListUseCaseTest {
    lateinit var sortingListUseCase: SortingListUseCase

    @Before
    fun setUp() {
        sortingListUseCase = SortingListUseCase()
    }

    @Test
    fun execute_ascending() {
        val unsortedList = TestUtil.createCurrencyInfoList()
        val result = sortingListUseCase.execute(unsortedList, SortedOrder.Ascending)
        val expectedList = unsortedList.sortedBy { it.name }
        for (i in result.indices) {
            assertEquals(result[i], expectedList[i])
        }
    }

    @Test
    fun execute_descending() {
        val unsortedList = TestUtil.createCurrencyInfoList()
        val result = sortingListUseCase.execute(unsortedList, SortedOrder.Descending)
        val expectedList = unsortedList.sortedByDescending { it.name }
        for (i in result.indices) {
            assertEquals(result[i], expectedList[i])
        }
    }

    @Test
    fun execute_unsorted() {
        val unsortedList = TestUtil.createCurrencyInfoList()
        val result = sortingListUseCase.execute(unsortedList, SortedOrder.Unsorted)
        val expectedList = unsortedList.sortedBy { it.name }
        for (i in result.indices) {
            assertEquals(result[i], expectedList[i])
        }
    }
}