package com.example.currencylist.data.repository

import com.example.currencylist.TestUtil
import com.example.currencylist.data.CurrencyInfo
import org.junit.Assert.*
import org.junit.Test

class ParserHelperTest {
    private val parserHelper: ParserHelper = ParserHelper()

    @Test
    fun test_parse_object() {
        val rawData = TestUtil.getCurrencyInfoObjectInString()
        val fakeObject = TestUtil.createCurrencyInfoObject()
        val result = parserHelper.parseObject<CurrencyInfo>(rawData)
        assertEquals(fakeObject, result)
    }

    @Test
    fun test_parse_list() {
        val rawData = TestUtil.getCurrencyInfoListInString()
        val fakeList = TestUtil.createCurrencyInfoList()
        val result = parserHelper.parseListObject<CurrencyInfo>(rawData)
        for (i in fakeList.indices) {
            assertEquals(fakeList[i], result[i])
        }
    }
}