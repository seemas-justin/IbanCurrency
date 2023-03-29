package com.ibancurrency.currencyconverter.domain

data class CurrencyItemEntity(
    val symbol: String,
    val conversionValue: Double,
    var calculatedValue: Double = 0.0
)
