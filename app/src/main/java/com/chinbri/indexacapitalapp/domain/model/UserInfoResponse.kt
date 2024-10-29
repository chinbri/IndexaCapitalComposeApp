package com.chinbri.indexacapitalapp.domain.model

import com.google.gson.annotations.SerializedName

data class UserInfoResponse(
    val email: String,
    @SerializedName("user_name")
    val userName: String,
    val accounts: List<Account>
)

data class Account(
    @SerializedName("account_number")
    val accountNumber: String,
    val type: String,
)