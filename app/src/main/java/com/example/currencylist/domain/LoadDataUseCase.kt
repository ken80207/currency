package com.example.currencylist.domain

import com.example.currencylist.data.CurrencyInfo
import com.example.currencylist.data.Resource
import com.example.currencylist.data.repository.ICurrencyRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class LoadDataUseCase @Inject constructor(
    private val repository: ICurrencyRepository
) {
    fun execute(): Flow<Resource<List<CurrencyInfo>>> =
        repository.getCurrencyInfoList()
}