package com.chinbri.indexacapitalapp.data.api

import com.chinbri.indexacapitalapp.domain.model.UserInfoResponse
import retrofit2.http.GET
import retrofit2.http.Header

interface IndexaCapitalAppApi {

    @GET("users/me")
    suspend fun getUser(
        @Header("Accept") accept: String = "*/*",
        @Header("X-AUTH-TOKEN") authToken: String
    ): UserInfoResponse

}