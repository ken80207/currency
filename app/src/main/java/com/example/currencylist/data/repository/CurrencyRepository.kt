package com.example.currencylist.data.repository

import com.example.currencylist.data.CurrencyInfo
import com.example.currencylist.data.Resource
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class CurrencyRepository @Inject constructor(
    private val localDataSource: ILocalDataSource,
): ICurrencyRepository {
    override fun getCurrencyInfoList(): Flow<Resource<List<CurrencyInfo>>> =
        localDataSource.getCurrencyInfoList()
}