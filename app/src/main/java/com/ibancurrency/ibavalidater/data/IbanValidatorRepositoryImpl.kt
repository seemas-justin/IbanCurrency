package com.ibancurrency.ibavalidater.data

import com.google.gson.Gson
import com.ibancurrency.ibavalidater.data.api.IbanValidationService
import com.ibancurrency.ibavalidater.data.models.IbanValidationError
import com.ibancurrency.ibavalidater.data.models.ValidateIbanResult
import com.ibancurrency.ibavalidater.domain.IbanValidatorRepository
import javax.inject.Inject

class IbanValidatorRepositoryImpl
@Inject constructor(
    private val ibanValidationService: IbanValidationService,
    private val gson: Gson
): IbanValidatorRepository {

    override suspend fun validateIban(iban: String) : Result<ValidateIbanResult?> {
        val res = ibanValidationService.validateIban(iban)
        if(res.isSuccessful){
            return Result.success(res.body())
        }else {
            if(res.code() == 422) {
                val ibanValidationError = gson.fromJson<IbanValidationError>(res.errorBody()?.string(), IbanValidationError::class.java)
                return Result.success(ValidateIbanResult(
                    isValid = false,
                    message = if (ibanValidationError.errorDetails.ibanNumber.isNotEmpty()) ibanValidationError.errorDetails.ibanNumber[0] else "Some exception"
                ))
            }
            return Result.failure(Throwable("Server exception"))
        }
    }


}
