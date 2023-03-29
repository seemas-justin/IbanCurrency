package com.ibancurrency.currencyconverter.data

import com.ibancurrency.currencyconverter.data.api.CurrencyConversionServices
import com.ibancurrency.currencyconverter.domain.CurrencyConverterRepository
import com.ibancurrency.currencyconverter.domain.CurrencyItemEntity
import com.ibancurrency.currencyconverter.domain.CurrencyListEntity
import javax.inject.Inject

class CurrencyConverterRepositoryImpl @Inject constructor(
    private val currencyConversionServices: CurrencyConversionServices
)
    : CurrencyConverterRepository {

    override suspend fun getCurrencyConversionValues() : Result<CurrencyListEntity> {
        val result = currencyConversionServices.getCurrencyConversionRate()
        if (result.isSuccessful) {
           val currencyList =  result.body()?.extras?.map {
                CurrencyItemEntity (
                    symbol = it.key,
                    conversionValue = it.value
                )
            }

            return Result.success(
                CurrencyListEntity(
                currencyItems = currencyList,
                timeStamp = result.body()?.timestamp,
                isSuccess = result.body()?.success
            )
            )
        }else{
           return Result.failure(Throwable("Some Exception Occured"))
        }
    }

}