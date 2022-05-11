package com.example.currencylist.domain

import com.example.currencylist.data.repository.ICurrencyRepository
import io.mockk.MockKAnnotations
import io.mockk.impl.annotations.RelaxedMockK
import io.mockk.verify
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Test

class LoadDataUseCaseTest {
    lateinit var loadDataUseCase: LoadDataUseCase

    @RelaxedMockK
    lateinit var repository: ICurrencyRepository

    @Before
    fun setUp() {
        MockKAnnotations.init(this)
        loadDataUseCase = LoadDataUseCase(repository)
    }

    @Test
    fun execute() = runBlocking {
        loadDataUseCase.execute().collect { }

        verify(exactly = 1) {
            repository.getCurrencyInfoList()
        }
    }
}