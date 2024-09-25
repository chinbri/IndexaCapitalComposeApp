package com.chinbri.indexacapitalapp.data.datasource.network

import com.chinbri.indexacapitalapp.domain.model.UserInfoResponse

class IndexaNetworkDataSourceImpl: IndexaNetworkDataSource {

    override suspend fun getUserInfo(token: String): UserInfoResponse {

        return UserInfoResponse(
            "John Doe",
            "jdoe"
        )

    }

}