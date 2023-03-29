package com.ibancurrency.currencyconverter.domain


interface CurrencyConverterRepository {
    suspend fun getCurrencyConversionValues(): Result<CurrencyListEntity>
}