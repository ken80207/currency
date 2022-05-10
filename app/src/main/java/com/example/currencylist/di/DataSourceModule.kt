package com.example.currencylist.di

import com.example.currencylist.data.repository.CurrencyRepository
import com.example.currencylist.data.repository.ICurrencyRepository
import com.example.currencylist.data.repository.ILocalDataSource
import com.example.currencylist.data.repository.LocalDataSource
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@InstallIn(SingletonComponent::class)
@Module
abstract class DataSourceModule {
    @Binds
    abstract fun bindLocalDataSource(
        localDataSource: LocalDataSource
    ): ILocalDataSource

    @Binds
    abstract fun bindCurrencyRepository(
        currencyRepository: CurrencyRepository
    ): ICurrencyRepository
}