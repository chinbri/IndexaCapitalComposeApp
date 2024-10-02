package com.chinbri.indexacapitalapp.data.datasource.local

import com.chinbri.indexacapitalapp.BuildConfig
import javax.inject.Inject

class IndexaLocalDataSourceImpl @Inject constructor(): IndexaLocalDataSource {

    override fun getAuthToken(): String = BuildConfig.API_TOKEN

}