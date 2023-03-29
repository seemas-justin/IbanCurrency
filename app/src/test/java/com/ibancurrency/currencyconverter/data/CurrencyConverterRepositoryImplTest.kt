package com.ibancurrency.currencyconverter.data

import com.ibancurrency.currencyconverter.data.api.CurrencyConversionServices
import com.ibancurrency.currencyconverter.data.models.CurrencySymbolsResponse
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.mockk
import kotlinx.coroutines.runBlocking
import okhttp3.ResponseBody
import org.junit.Assert
import org.junit.Test
import retrofit2.Response

class CurrencyConverterRepositoryImplTest {


    @Test
    fun testIfCurrencyListsAreRecievedItsReturned(){

        // arrange
        val currencyConversionServices: CurrencyConversionServices = mockk()
        coEvery { currencyConversionServices.getCurrencyConversionRate() } returns Response.success(
            CurrencySymbolsResponse(
            success = true,
            timestamp = 1234444,
            extras = mapOf(
                "AED" to 234444.78,
                "INR" to 2856.0
            ))
        )
         val sut = CurrencyConverterRepositoryImpl(
            currencyConversionServices = currencyConversionServices
         )
        runBlocking {
            // act
            val res = sut.getCurrencyConversionValues()

            //assert
            Assert.assertEquals(res.isSuccess, true)
            Assert.assertEquals(res.getOrNull()?.currencyItems?.size, 2)
        }

        coVerify { currencyConversionServices.getCurrencyConversionRate() }

    }

    @Test
    fun testIfFailureCondition(){
        // arrange
        val currencyConversionServices: CurrencyConversionServices = mockk()
        coEvery { currencyConversionServices.getCurrencyConversionRate() } returns Response.error(401, ResponseBody.create(null,"Unauthorrised"))

        val sut = CurrencyConverterRepositoryImpl(
            currencyConversionServices = currencyConversionServices
        )
        runBlocking {
            //act
            val res = sut.getCurrencyConversionValues()
            //assert
            Assert.assertEquals(res.isFailure, true)
        }

        coVerify { currencyConversionServices.getCurrencyConversionRate() }

    }
}