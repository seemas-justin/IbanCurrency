package com.ibancurrency.ibavalidater.data.api

import com.ibancurrency.ibavalidater.data.models.ValidateIbanResult
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface IbanValidationService {
    @GET("bank_data/iban_validate")
    suspend fun validateIban(@Query("iban_number")  ibanNumber: String): Response<ValidateIbanResult>
}