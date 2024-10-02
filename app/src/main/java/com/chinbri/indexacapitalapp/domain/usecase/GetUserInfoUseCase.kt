package com.chinbri.indexacapitalapp.domain.usecase

import com.chinbri.indexacapitalapp.data.datasource.local.IndexaLocalDataSource
import com.chinbri.indexacapitalapp.data.datasource.network.IndexaNetworkDataSource
import com.chinbri.indexacapitalapp.domain.model.UserInfoResponse
import javax.inject.Inject

interface GetUserInfoUseCase: BaseUseCase<Unit, UserInfoResponse>

class GetUserInfoUseCaseImpl @Inject constructor(
    private val indexaLocalDataSource: IndexaLocalDataSource,
    private val indexaNetworkDataSource: IndexaNetworkDataSource
) :
    GetUserInfoUseCase  {

    override suspend fun invoke(request: Unit): UserInfoResponse {
        return indexaNetworkDataSource.getUserInfo(
            indexaLocalDataSource.getAuthToken()
        )
    }

}