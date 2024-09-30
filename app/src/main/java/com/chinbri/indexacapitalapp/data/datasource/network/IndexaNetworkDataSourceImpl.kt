package com.chinbri.indexacapitalapp.data.datasource.network

import com.chinbri.indexacapitalapp.domain.model.UserInfoResponse
import javax.inject.Inject

class IndexaNetworkDataSourceImpl @Inject constructor(): IndexaNetworkDataSource {

    override suspend fun getUserInfo(token: String): UserInfoResponse {

        return UserInfoResponse(
            "John Doe",
            "jdoe"
        )

    }

}