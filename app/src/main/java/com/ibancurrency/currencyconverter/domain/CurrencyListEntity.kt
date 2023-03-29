package com.ibancurrency.currencyconverter.domain

data class CurrencyListEntity(
    val timeStamp: Long?,
    val currencyItems: List<CurrencyItemEntity>?,
    val isSuccess: Boolean?
)