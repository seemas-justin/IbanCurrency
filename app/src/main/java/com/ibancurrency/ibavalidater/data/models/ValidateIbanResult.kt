package com.ibancurrency.ibavalidater.data.models

import com.google.gson.annotations.SerializedName

data class ValidateIbanResult(
    @SerializedName("valid")
    val isValid: Boolean,
    @SerializedName("message")
    val message: String?
)
