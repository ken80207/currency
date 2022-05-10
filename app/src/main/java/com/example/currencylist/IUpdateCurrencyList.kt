package com.example.currencylist

interface IUpdateCurrencyList<T> {
    fun updateList(list: List<T>)
    fun setItemClickListener(listener: IItemClickListener<T>)
}