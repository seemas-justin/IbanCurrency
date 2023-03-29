package com.ibancurrency.ibavalidater.domain

import com.ibancurrency.ibavalidater.data.models.ValidateIbanResult


interface IbanValidatorRepository {
    suspend fun validateIban(iban: String): Result<ValidateIbanResult?>
}