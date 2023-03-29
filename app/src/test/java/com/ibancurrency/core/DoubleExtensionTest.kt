package com.ibancurrency.core

import org.junit.Assert
import org.junit.Test

class DoubleExtensionTest {
    @Test
    fun testIfDoubleValueWith1decimalPointIsConverted(){

        // arrange
        val oneDecimalPointValue = 5.1

        // act
        val stringValue = oneDecimalPointValue.toCurrencyDecimals()

        // assert
        Assert.assertEquals("5.1", stringValue)
    }

    @Test
    fun testIfDoubleValueWith2decimalPointIsConverted(){

        // arrange
        val oneDecimalPointValue = 5.19

        // act
        val stringValue = oneDecimalPointValue.toCurrencyDecimals()

        // assert
        Assert.assertEquals("5.19", stringValue)
    }

    @Test
    fun testIfDoubleValueWith3decimalPointIsConverted(){

        // arrange
        val oneDecimalPointValue = 5.198

        // act
        val stringValue = oneDecimalPointValue.toCurrencyDecimals()

        // assert
        Assert.assertEquals("5.198", stringValue)
    }

    @Test
    fun testIfDoubleValueWith4decimalPointIsConverted(){

        // arrange
        val oneDecimalPointValue = 5.1983

        // act
        val stringValue = oneDecimalPointValue.toCurrencyDecimals()

        // assert
        Assert.assertEquals("5.198", stringValue)
    }

    @Test
    fun testIfDoubleValueWith8decimalPointIsConverted(){

        // arrange
        val oneDecimalPointValue = 5.19831234

        // act
        val stringValue = oneDecimalPointValue.toCurrencyDecimals()

        // assert
        Assert.assertEquals("5.198", stringValue)
    }

}