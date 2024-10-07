package com.chinbri.indexacapitalapp.domain.model

import com.google.gson.annotations.SerializedName

data class AccountInfoResponse(
    @SerializedName("account_number")
    val accountNumber: String,
)
