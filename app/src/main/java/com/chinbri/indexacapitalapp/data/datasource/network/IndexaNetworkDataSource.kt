package com.chinbri.indexacapitalapp.data.datasource.network

import com.chinbri.indexacapitalapp.domain.model.UserInfoResponse

interface IndexaNetworkDataSource {

    suspend fun getUserInfo(token: String): UserInfoResponse

}