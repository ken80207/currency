package com.example.currencylist.data.repository

import com.example.currencylist.TestUtil
import com.example.currencylist.data.Resource
import io.mockk.MockKAnnotations
import io.mockk.every
import io.mockk.impl.annotations.RelaxedMockK
import io.mockk.verify
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.test.runTest
import org.junit.Assert.*

import org.junit.Before
import org.junit.Test

class CurrencyRepositoryTest {
    @RelaxedMockK
    lateinit var localDataSource: LocalDataSource

    lateinit var currencyRepository: CurrencyRepository
    @Before
    fun setUp() {
        MockKAnnotations.init(this)
        currencyRepository = CurrencyRepository(localDataSource)
    }

    @Test
    fun getCurrencyInfoList_success() = runTest {
        val fakeList = TestUtil.createCurrencyInfoList()
        every { localDataSource.getCurrencyInfoList() } returns flow {
            emit(Resource.Success(fakeList))
        }

        currencyRepository.getCurrencyInfoList().collect { resource ->
            assertTrue(resource is Resource.Success<*>)
        }

        verify(exactly = 1) { localDataSource.getCurrencyInfoList() }
    }

    @Test
    fun getCurrencyInfoList_error() = runTest {
        every { localDataSource.getCurrencyInfoList() } returns flow {
            emit(Resource.Error())
        }

        currencyRepository.getCurrencyInfoList().collect { resource ->
            assertTrue(resource is Resource.Error<*>)
        }

        verify(exactly = 1) { localDataSource.getCurrencyInfoList() }
    }
}