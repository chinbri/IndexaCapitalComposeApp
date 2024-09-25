package com.chinbri.indexacapitalapp.domain.model

import com.google.gson.annotations.SerializedName

data class UserInfoResponse(
    val email: String,
    @SerializedName("user_name")
    val userName: String,
)