package com.ibancurrency.currencyconverter.data.api

import com.ibancurrency.currencyconverter.data.models.CurrencySymbolsResponse
import retrofit2.Response
import retrofit2.http.GET

interface CurrencyConversionServices {

    @GET("fixer/latest?base=KWD")
    suspend fun getCurrencyConversionRate(): Response<CurrencySymbolsResponse>
}