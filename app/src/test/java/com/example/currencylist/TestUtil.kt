package com.example.currencylist

import com.example.currencylist.data.CurrencyInfo

object TestUtil {
    fun createCurrencyInfoList(): List<CurrencyInfo> = listOf(
        generateCurrencyInfo("BTC", "Bitcoin", "BTC"),
        generateCurrencyInfo("ETH", "Ethereum", "ETH"),
        generateCurrencyInfo("XRP", "XRP", "XRP"),
        generateCurrencyInfo("BCH", "Bitcoin Cash", "BCH")
    )

    fun createCurrencyInfoObject(): CurrencyInfo =
        generateCurrencyInfo("BTC", "Bitcoin", "BTC")

    fun getCurrencyInfoListInString(): String = "[\n" +
            "  {\n" +
            "    \"id\": \"BTC\",\n" +
            "    \"name\": \"Bitcoin\",\n" +
            "    \"symbol\": \"BTC\"\n" +
            "  },\n" +
            "  {\n" +
            "    \"id\": \"ETH\",\n" +
            "    \"name\": \"Ethereum\",\n" +
            "    \"symbol\": \"ETH\"\n" +
            "  },\n" +
            "  {\n" +
            "    \"id\": \"XRP\",\n" +
            "    \"name\": \"XRP\",\n" +
            "    \"symbol\": \"XRP\"\n" +
            "  },\n" +
            "  {\n" +
            "    \"id\": \"BCH\",\n" +
            "    \"name\": \"Bitcoin Cash\",\n" +
            "    \"symbol\": \"BCH\"\n" +
            "  }\n" +
            "]"

    fun getCurrencyInfoObjectInString(): String = "{\n" +
            "  \"id\": \"BTC\",\n" +
            "  \"name\": \"Bitcoin\",\n" +
            "  \"symbol\": \"BTC\"\n" +
            "}"

    private fun generateCurrencyInfo(id: String, name: String, symbol: String): CurrencyInfo =
        CurrencyInfo(
            id = id,
            name = name,
            symbol = symbol
        )
}