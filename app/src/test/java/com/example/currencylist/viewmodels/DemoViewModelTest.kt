package com.example.currencylist.viewmodels

import com.example.currencylist.TestUtil
import com.example.currencylist.data.Resource
import com.example.currencylist.domain.LoadDataUseCase
import com.example.currencylist.domain.SortedRuleUseCase
import com.example.currencylist.domain.SortingListUseCase
import com.example.currencylist.viewmodels.data.SortedOrder
import io.mockk.MockKAnnotations
import io.mockk.every
import io.mockk.impl.annotations.RelaxedMockK
import io.mockk.verify
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.test.UnconfinedTestDispatcher
import kotlinx.coroutines.test.setMain
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test

@ExperimentalCoroutinesApi
class DemoViewModelTest {
    @RelaxedMockK
    lateinit var loadDataUseCase: LoadDataUseCase

    @RelaxedMockK
    lateinit var sortingListUseCase: SortingListUseCase

    @RelaxedMockK
    lateinit var sortedRuleUseCase: SortedRuleUseCase

    lateinit var demoViewModel: DemoViewModel

    private val testDispatcher = UnconfinedTestDispatcher()

    @Before
    fun setUp() {
        MockKAnnotations.init(this)
        Dispatchers.setMain(testDispatcher)
        demoViewModel = DemoViewModel(
            loadDataUseCase,
            sortingListUseCase,
            sortedRuleUseCase,
            testDispatcher
        )
    }

    @Test
    fun fetchData_success() {
        val fakeCurrencyList = TestUtil.createCurrencyInfoList()
        every { loadDataUseCase.execute() } returns flow {
            Resource.Success(fakeCurrencyList)
        }

        demoViewModel.fetchData()
        assertEquals(DemoViewModel.UiState.InitState, demoViewModel.uiState.value)

        verify(exactly = 1) { loadDataUseCase.execute() }
    }

    @Test
    fun sortData() {
        val fakeSortedCurrencyList = TestUtil.createCurrencyInfoList().sortedBy { it.name }
        val fakeUnSortedOrder = SortedOrder.Unsorted
        every { sortedRuleUseCase.execute(any()) } returns fakeUnSortedOrder
        every { sortingListUseCase.execute(any(), any()) } returns fakeSortedCurrencyList
        demoViewModel.sortData()

        assertEquals(fakeUnSortedOrder, demoViewModel.currentSortedOrder.value)
        assertEquals(fakeSortedCurrencyList, demoViewModel.list.value)
    }
}