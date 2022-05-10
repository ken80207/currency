package com.example.currencylist.data.repository

import com.example.currencylist.data.CurrencyInfo
import com.example.currencylist.data.Resource
import kotlinx.coroutines.flow.Flow

interface ICurrencyRepository {
    fun getCurrencyInfoList(): Flow<Resource<List<CurrencyInfo>>>
}