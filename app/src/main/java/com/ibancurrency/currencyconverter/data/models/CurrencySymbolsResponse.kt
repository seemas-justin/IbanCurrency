package com.ibancurrency.currencyconverter.data.models

import com.google.gson.annotations.SerializedName

data class CurrencySymbolsResponse (
    @SerializedName("success")
    val success: Boolean,
    @SerializedName("rates")
    val extras: Map<String, Double>,
    @SerializedName("timestamp")
    val timestamp: Long

)