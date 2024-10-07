package com.chinbri.indexacapitalapp.domain.usecase

import com.chinbri.indexacapitalapp.data.datasource.local.IndexaLocalDataSource
import com.chinbri.indexacapitalapp.data.datasource.network.IndexaNetworkDataSource
import com.chinbri.indexacapitalapp.domain.model.AccountInfoResponse
import javax.inject.Inject

interface GetAccountInfoUseCase: BaseUseCase<String, AccountInfoResponse>

class GetAccountInfoUseCaseImpl @Inject constructor(
    private val indexaLocalDataSource: IndexaLocalDataSource,
    private val indexaNetworkDataSource: IndexaNetworkDataSource
) :
    GetAccountInfoUseCase  {

    override suspend fun invoke(request: String): AccountInfoResponse {
        return indexaNetworkDataSource.getAccountInfo(
            authToken = indexaLocalDataSource.getAuthToken(),
            account = request
        )
    }

}