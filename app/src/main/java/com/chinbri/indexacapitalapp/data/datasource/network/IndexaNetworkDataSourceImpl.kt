package com.chinbri.indexacapitalapp.data.datasource.network

import com.chinbri.indexacapitalapp.data.api.IndexaCapitalAppApi
import com.chinbri.indexacapitalapp.domain.model.AccountInfoResponse
import com.chinbri.indexacapitalapp.domain.model.UserInfoResponse
import javax.inject.Inject

class IndexaNetworkDataSourceImpl @Inject constructor(
    private val indexaCapitalAppApi: IndexaCapitalAppApi
) : IndexaNetworkDataSource {

    override suspend fun getUserInfo(token: String): UserInfoResponse =
        indexaCapitalAppApi.getUser(authToken = token)

    override suspend fun getAccountInfo(authToken: String, account: String): AccountInfoResponse =
        indexaCapitalAppApi.getAccountInfo(authToken = authToken, account = account)

}