package com.example.currencylist.data.repository

import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import javax.inject.Inject

class ParserHelper @Inject constructor() {
    inline fun <reified T> parseObject(inputString: String): T {
        val currencyType = object : TypeToken<T>() {}.type
        return Gson().fromJson(inputString, currencyType)
    }

    inline fun <reified T> parseListObject(inputString: String): List<T> {
        val currencyType = object : TypeToken<List<T>>() {}.type
        return Gson().fromJson(inputString, currencyType)
    }
}