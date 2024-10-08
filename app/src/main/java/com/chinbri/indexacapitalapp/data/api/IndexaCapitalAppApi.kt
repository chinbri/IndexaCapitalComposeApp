package com.chinbri.indexacapitalapp.data.api

import com.chinbri.indexacapitalapp.domain.model.AccountInfoResponse
import com.chinbri.indexacapitalapp.domain.model.UserInfoResponse
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Path

interface IndexaCapitalAppApi {

    @GET("users/me")
    suspend fun getUser(
        @Header("Accept") accept: String = "*/*",
        @Header("X-AUTH-TOKEN") authToken: String
    ): UserInfoResponse


    @GET("accounts/{account_number}/portfolio")
    suspend fun getAccountInfo(
        @Header("Accept") accept: String = "*/*",
        @Header("X-AUTH-TOKEN") authToken: String,
        @Path("account_number") account: String
    ): AccountInfoResponse

}