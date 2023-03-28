package com.kwdcurrencyconverter.core

import kotlin.math.roundToInt

fun Double.toCurrencyDecimals() : String {
    return ( (this * 1000.0).roundToInt() / 1000.0).toString()
}