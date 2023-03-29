package com.ibancurrency.ibavalidater.data.models

import com.google.gson.annotations.SerializedName

data class IbanValidationError (
    @SerializedName("message")
    var message: String,
    @SerializedName("errors")
    var errorDetails: IbanValidationDetails
)

data class IbanValidationDetails(
    @SerializedName("iban_number")
    var ibanNumber: Array<String>
)