package com.example.currencylist

import com.example.currencylist.data.CurrencyInfo

object TestUtil {
    fun createCurrencyInfoList(): List<CurrencyInfo> = listOf(
        generateCurrencyInfo("BTC", "Bitcoin", "BTC"),
        generateCurrencyInfo("ETH", "Ethereum", "ETH"),
        generateCurrencyInfo("XRP", "XRP", "XRP"),
        generateCurrencyInfo("BCH", "Bitcoin Cash", "BCH")
    )

    private fun generateCurrencyInfo(id: String, name: String, symbol: String): CurrencyInfo =
        CurrencyInfo(
            id = id,
            name = name,
            symbol = symbol
        )
}